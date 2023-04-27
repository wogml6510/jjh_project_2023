package com.jjh.exam.demo.service;

import org.springframework.stereotype.Service;

import com.jjh.exam.demo.repository.ReactionPointRepository;
import com.jjh.exam.demo.vo.ResultData;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
	}

	public ResultData actorCanMakeReactionPoint(int actorId, String relTypeCode, int relId) {
		if (actorId == 0) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}
		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(relId, relTypeCode,
				actorId);

		if (sumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "리액션이 불가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}

		return ResultData.from("S-a", "리액션이 가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}

	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addGoodReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.increseGoodReactionPoint(relId);
		}
		return ResultData.from("S-1", "좋아요 처리가 되었습니다.");
	}

	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.increseGoodReactionPoint(relId);
		}
		return ResultData.from("S-1", "싫어요 처리가 되었습니다.");
	}

	public ResultData deleteGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteGoodReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreseGoodReactionPoint(relId);
		}
		return ResultData.from("S-1", "좋아요 취소처리가 되었습니다.");
	}

	public ResultData deleteBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteBadReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreseBadReactionPoint(relId);
		}
		return ResultData.from("S-1", "싫어요 취소처리가 되었습니다.");
	}
}
