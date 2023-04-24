package com.jjh.exam.demo.service;

import org.springframework.stereotype.Service;

import com.jjh.exam.demo.repository.BoardRepository;
import com.jjh.exam.demo.vo.Board;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRpository) {
		this.boardRepository = boardRpository;
	}
	
	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}
