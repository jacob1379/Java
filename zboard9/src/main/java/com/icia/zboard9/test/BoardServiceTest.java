package com.icia.zboard9.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.icia.zboard9.dto.WriteDto;
import com.icia.zboard9.entity.Board;
import com.icia.zboard9.service.BoardService;

public class BoardServiceTest {
	BoardService service = BoardService.getInstance();
	
	@Test
	public void wrtieTest() {
		WriteDto dto = new WriteDto("1번", "1번 글", "string", null);
		Board board = service.write(dto);
		assertEquals(1, board.getBno());
	}
}
