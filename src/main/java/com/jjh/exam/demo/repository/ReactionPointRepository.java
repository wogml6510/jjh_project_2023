package com.jjh.exam.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {

	@Select("""
			SELECT IFNULL(SUM(RP.point),0) AS s
			FROM reactionPoint AS RP 
			WHERE RP.relTypeCode = 'article'
			AND RP.relId = #{relId}
			AND RP.memberId = #{memberId}
			""")
	public int getSumReactionPointByMemberId(int relId, String relTypeCode, int memberId);

	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = 1
			""")
	public void addGoodReactionPoint(int memberId, String relTypeCode, int relId);
	
	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = -1
			""")
	public void addBadReactionPoint(int memberId, String relTypeCode, int relId);

	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{memberId}
			""")
	public void deleteGoodReactionPoint(int memberId, String relTypeCode, int relId);
	
	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{memberId}
			""")
	public void deleteBadReactionPoint(int memberId, String relTypeCode, int relId);

}












