package com.lsw.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lsw.app.qna.QnaMapper;
import com.lsw.app.qna.QnaVO;
import com.lsw.app.util.Pager;

@SpringBootTest
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
		
		List<QnaVO> ar = qnaMapper.getList(pager);
		
		assertNotEquals(0, ar.size());
		
	}
	
//	@Test
//	void addTest() throws Exception {
//		for(int i=4; i<110; i++) {
//			QnaVO qnaVO = new QnaVO();
//			qnaVO.setBoardContents("c"+i);
//			qnaVO.setBoardTitle("t"+i);
//			qnaVO.setBoardWriter("w"+i);
//			qnaVO.setRef(0L);
//			qnaVO.setStep(0L);
//			qnaVO.setDex(0L);
//			int result = qnaMapper.add(qnaVO);
//		}
//	}


}
