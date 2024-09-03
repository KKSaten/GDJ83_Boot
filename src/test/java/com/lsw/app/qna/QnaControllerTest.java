package com.lsw.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

	private WebApplicationContext ctx;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void test() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	void getListTest() throws Exception {
		
		//Map 키 중복 허용하지 않음 ex) a, 1 => a, 2
		//근데 얘는 키 중복을 허용함 ex) a, 1 => a, 2 -> a {1,2}
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page", "1");
		map.add("kind", "k1");
		map.add("search", "2");
		
//		Map<String, String> map2 = new HashMap<>();
//		map2.put("page", "1");
//		map2.put("kind", "k1");
//		map2.put("search", "2");
		
		mockMvc.perform(
					get("/qna/list")
					.params(map)
//					.param("page", "1")
//					.param("kind", "k1")
//					.param("search", "2")
					
				)
				.andDo(print());
	}
	
	
	@Test
	public void getDetailTest() throws Exception {
		mockMvc.perform(
					get("/qna/detail")
					.param("boardNum", "110")
				)
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	
//	@Test
//	public void addTest() throws Exception {
//		
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("boardWriter", "writer");
//		map.add("boardTitle", "title");
//		map.add("boardContents", "contents");
//		
//		mockMvc.perform(
//					get("/qna/add")
//					.params(map)
////					.param("boardWriter", "writer")
////					.param("boardTitle", "title")
////					.param("boardContents", "contents")
//				
//				)
//				.andDo(print());
//		
//	}
	
	
	
}
