package com.study.interview.spring.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CrmIdServiceImpl implements CrmIdService {

	@Override
	public List<CrmIdEntity> query(Long f_corp_id) {
		List<CrmIdEntity> list = new ArrayList<CrmIdEntity>();
		list.add(new CrmIdEntity(12124L,f_corp_id));
		list.add(new CrmIdEntity(12125L,f_corp_id));
		int a = 10 / 0;
		return list;
	}

}
