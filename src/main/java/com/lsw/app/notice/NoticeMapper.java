package com.lsw.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	
	public List<NoticeVO> getList(Long a) throws Exception;
	
}
