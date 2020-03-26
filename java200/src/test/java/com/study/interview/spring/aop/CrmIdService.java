package com.study.interview.spring.aop;

import java.util.List;

public interface CrmIdService {
	/**
	 * 
	 * @Title: query
	 * @Description: 查询客户
	 * <p>1. 业务执行前， 需要权限验证</p>
	 * <p>2. 业务执行后， 需要打印日志，统计查询时间</p>
	 * <p>3. 业务执行后， 需要打印日志，统计查询时间</p>
	 * @param f_corp_id
	 * @return
	 * @author shicy
	 * @date 2020-03-26 05:37:04
	 */
	public List<CrmIdEntity> query(Long f_corp_id);
}
