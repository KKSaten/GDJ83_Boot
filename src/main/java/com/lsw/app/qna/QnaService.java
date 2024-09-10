package com.lsw.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lsw.app.util.FileManager;
import com.lsw.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;
	
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		pager.setPage(1L);
		pager.makeRow();
		log.info("Upload Path : {}", upload);
		
		return qnaMapper.getList(pager);
	}
	
	public int add(QnaVO qnaVO, MultipartFile [] attaches) throws Exception {
		
		int result = qnaMapper.add(qnaVO);

		result = qnaMapper.refUpdate(qnaVO);
		
		//파일을 HDD에 저장 후 DB에 정보를 추가
		if(attaches != null) {
			for(MultipartFile mf : attaches) {			
				if(mf.isEmpty() || mf == null) {
					continue;
				}
				String fileName = fileManager.fileSave(upload+name, mf); // D:/upload/qna
				
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(mf.getOriginalFilename());
				qnaFileVO.setBoardNum(qnaVO.getBoardNum());
				
				result = qnaMapper.addFile(qnaFileVO);
				
			}
		}
		
		
		
		return result;
	}
	
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception {
		
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
	
	
	
	
	
	
	
	
}
