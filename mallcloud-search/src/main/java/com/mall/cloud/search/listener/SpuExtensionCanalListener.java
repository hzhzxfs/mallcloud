package com.mall.cloud.search.listener;


import cn.throwx.canal.gule.model.CanalBinLogResult;
import cn.throwx.canal.gule.support.processor.BaseCanalBinlogEventProcessor;
import com.mall.cloud.api.product.bo.EsProductBO;
import com.mall.cloud.common.cache.constant.CacheNames;
import com.mall.cloud.common.cache.util.CacheManagerUtil;
import com.mall.cloud.common.exception.mallcloudException;
import com.mall.cloud.common.util.Json;
import com.mall.cloud.search.bo.SpuExtensionBO;
import com.mall.cloud.search.constant.EsIndexEnum;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SpuExtensionCanalListener extends BaseCanalBinlogEventProcessor<SpuExtensionBO> {

    private static final Logger log = LoggerFactory.getLogger(SpuExtensionCanalListener.class);

    @Autowired
    private CacheManagerUtil cacheManagerUtil;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 插入商品，此时插入es
     */
    @Override
    protected void processInsertInternal(CanalBinLogResult<SpuExtensionBO> result) {
        System.out.println();
    }

    /**
     * 更新商品，删除商品索引，再重新构建一个
     */
    @Override
    protected void processUpdateInternal(CanalBinLogResult<SpuExtensionBO> result) {
        // 更新之后的数据
        SpuExtensionBO afterData = result.getAfterData();

        // 清除缓存
        cacheManagerUtil.evictCache(CacheNames.SPU_EXTENSION_KEY, afterData.getSpuId().toString());

        UpdateRequest request = new UpdateRequest(EsIndexEnum.PRODUCT.value(), String.valueOf(afterData.getSpuId()));

        EsProductBO esProductBO = new EsProductBO();
        // 可售库存
        esProductBO.setSpuId(afterData.getSpuId());
        esProductBO.setStock(afterData.getStock());
        esProductBO.setHasStock(afterData.getStock() != 0);
        esProductBO.setSaleNum(afterData.getSaleNum());

        request.doc(Json.toJsonString(esProductBO), XContentType.JSON);
        try {
            UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            log.info(updateResponse.toString());
        } catch (IOException e) {
            log.error(e.toString());
            throw new mallcloudException("更新es信息异常",e);
        }
    }
}
