package com.lsw.app.qna;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private long ref;
	private long step;
	private long dex;
	private List<QnaVO> ar;


}
