package com.mall.cloud.biz.service;

import com.mall.cloud.biz.model.AttachFileGroup;
import com.mall.cloud.biz.vo.AttachFileGroupVO;

import java.util.List;

public interface AttachFileGroupService {

	/**
	 * 获取列表
	 * @return 列表数据
	 */
	List<AttachFileGroupVO> list();

	/**
	 * 根据id获取
	 *
	 * @param attachFileGroupId id
	 * @return
	 */
	AttachFileGroupVO getByAttachFileGroupId(Long attachFileGroupId);

	/**
	 * 保存
	 * @param attachFileGroup
	 */
	void save(AttachFileGroup attachFileGroup);

	/**
	 * 更新
	 * @param attachFileGroup
	 */
	void update(AttachFileGroup attachFileGroup);

	/**
	 * 根据id删除
	 * @param attachFileGroupId
	 */
	void deleteById(Long attachFileGroupId);
}
