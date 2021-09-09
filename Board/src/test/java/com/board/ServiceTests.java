package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.service.BoardServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class ServiceTests {
	@Autowired
	private BoardServiceImpl boardService;
	private BoardMapper boardMapper;

	@Test
	public void testOfRegisterBoard() {
		BoardDTO params = new BoardDTO();
		params.setTitle("번 게시글 제목생성");
		params.setContent("번 게시글 내용 생성");
		params.setWriter("테스터");
		//params.setIdx((long)1);
		boolean result = boardService.registerBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardService.getBoardDetail((long) 2);
		try {
			String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);


			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testOfDelete() {
		boolean result = boardService.deleteBoard((long) 2);
		if (result == true) {
			BoardDTO board = boardService.getBoardDetail((long) 2);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testSelectList() {
			List<BoardDTO> boardList = boardService.getBoardList();
			if (CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDTO board : boardList) {
					System.out.println("=========================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("=========================");
				}
			}
		}
	
	
	

}
