package gntp.lesson.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import gntp.lesson.guestbook.util.ConnectionManagerV2;
import gntp.lesson.guestbook.vo.GuestbookVO;
import gntp.lesson.guestbook.vo.ReplyVO;

@Repository("guestbookDAO")
public class GuestbookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insertOne(GuestbookVO book) throws SQLException {
		boolean flag = false;
		//connection 연결확인 하세요
		
		int affectedCount = sqlSession.insert("mapper.guestbook.insertBook", book);
		if(affectedCount > 0) {
			flag = true;
		}
		return flag;
	}
	
	public ArrayList<GuestbookVO> selectAll() throws SQLException{
		ArrayList<GuestbookVO> list = null;
		list = (ArrayList<GuestbookVO>)sqlSession.selectList("selectAllBookList");
		
		return list;
	}

	public GuestbookVO selectOneForUpdate(String seq) throws SQLException {
		// TODO Auto-generated method stub
		GuestbookVO book = null;
		book = (GuestbookVO)sqlSession.selectOne("mapper.guestbook.selectBookForUpdate", Integer.parseInt(seq));
		System.out.println(book);
		return book;
	}
	
	public GuestbookVO selectOne(String seq,String token) throws SQLException {
		// TODO Auto-generated method stub
		GuestbookVO book = null;

		if(token!=null) {
			int affectedCount = sqlSession.update("mapper.guestbook.updateReadCount", Integer.parseInt(seq));
			boolean flag = false;
			if(affectedCount>0) {
				flag = true;
			}
		}
		
		book = (GuestbookVO)sqlSession.selectOne("mapper.guestbook.selectBookBySeq", 
																	Integer.parseInt(seq));
		System.out.println(book);	
		return book;
	}

	public boolean updateOne(GuestbookVO book) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;

		int affectedCount = sqlSession.update("mapper.guestbook.updateBook", book);
		if(affectedCount>0) {
			flag = true;
		}
		
		return flag;
	}

	public boolean deleteOne(String seq) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		//connection 연결확인 하세요
		int seqInt = Integer.parseInt(seq);
		sqlSession.delete("mapper.guestbook.deleteReply", seqInt);
		int affectedCount = sqlSession.delete("mapper.guestbook.deleteBook", seqInt);
		if(affectedCount>0) {
			flag = true;
		}
		
		return flag;
	}

	public boolean insertReply(ReplyVO vo) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		//connection 연결확인 하세요
		int affectedCount = sqlSession.insert("mapper.guestbook.insertReply", vo);
		if(affectedCount>0) {
			flag = true;
		}
		
		return flag;
	}
}







