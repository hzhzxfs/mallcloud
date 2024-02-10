package com.mall.cloud.leaf.service;

import com.mall.cloud.leaf.IDGen;
import com.mall.cloud.leaf.common.Result;
import com.mall.cloud.leaf.exception.InitException;
import com.mall.cloud.leaf.segment.SegmentIDGenImpl;
import com.mall.cloud.leaf.segment.dao.IDAllocDao;
import com.mall.cloud.leaf.segment.dao.impl.IDAllocDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Service("SegmentService")
public class SegmentService {

	private final Logger logger = LoggerFactory.getLogger(SegmentService.class);

	private final IDGen idGen;

	public SegmentService(DataSource dataSource) throws InitException {
		// Config Dao
		IDAllocDao dao = new IDAllocDaoImpl(dataSource);

		// Config ID Gen
		idGen = new SegmentIDGenImpl();
		((SegmentIDGenImpl) idGen).setDao(dao);
		if (idGen.init()) {
			logger.info("Segment Service Init Successfully");
		}
		else {
			throw new InitException("Segment Service Init Fail");
		}

	}

	public Result getId(String key) {
		return idGen.get(key);
	}

	public SegmentIDGenImpl getIdGen() {
		if (idGen instanceof SegmentIDGenImpl) {
			return (SegmentIDGenImpl) idGen;
		}
		return null;
	}

}
