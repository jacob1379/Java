package com.icia.zboard9.service;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.Part;

import com.icia.zboard9.dto.WriteDto;
import com.icia.zboard9.entity.Board;

public class BoardService {
	private List<Board> list = new Vector<>();
	public int bno = 1;
	private final Integer PAGESIZE = 10;
	private final String UPLOAD_FOLDER = "c:/upload/";
	private final String UPLOAD_URL = "http://localhost:8081/images/";
	
	private static BoardService service = new BoardService();
	public static BoardService getInstance() {
		return service;
	}
	
	private BoardService() {
//		for(int i=1; i<123; i++) {
//			write(new WriteDto(i+"",i+"번째 글", "spring", null));
//		}
	}
	
	public Board write(WriteDto dto) {
		Board board = dto.toEntity();
		Part a = dto.getAttachment();
		
		if(a!=null && !a.getSubmittedFileName().equals("")) {
			String name = System.currentTimeMillis()+"-"+a.getSubmittedFileName();
			try {
				a.write(UPLOAD_FOLDER+name);
				board.init(UPLOAD_URL, name, bno++);
			} catch(IOException e) {
				e.printStackTrace();
				board.init(bno++);
			}
		} else {
			board.init(bno++);
		}
		list.add(0, board);
		return board;
	}
	
}	
