package coffee12.kr.farmstory.service.board;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;
import coffee12.kr.farmstory.vo.BoardVO;

public class CommentService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String method = req.getMethod();



		if(method.equals("POST")) {

			String parent = req.getParameter("parent");
			String uid = req.getParameter("uid");
			String nick = req.getParameter("nick");
			String content = req.getParameter("content");
			String regip = req.getRemoteAddr();



			Connection conn = DBConfig.getConnection();
			CallableStatement call = conn.prepareCall(SQL.INSERT_COMMENT);
			call.setString(1, parent);
			call.setString(2, content);
			call.setString(3, uid);
			call.setString(4, regip);

			call.execute();

			call.close();
			conn.close();

			// 리턴할 데이터 구하기
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			String date = sdf.format(new Date());

			JSONObject json = new JSONObject();
			json.put("content", content);
			json.put("nick", nick);
			json.put("date", date);


			return json.toString();
			
		} else {

			String parent = req.getParameter("parent");
			
			Connection conn = DBConfig.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_COMMENT);
			pstmt.setString(1, parent);
			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardVO> list = new ArrayList<>();
			while(rs.next()) {
				BoardVO vo = new BoardVO(); // list 배열안에 vo 객체 여러개가 담김 - new BoardVO()가 안에 있어야 여러개 생성 가능
				vo.setSeq(rs.getInt("seq"));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString("rdate"));
				vo.setNick(rs.getString(12));

				list.add(vo);
			}

			rs.close();
			pstmt.close();
			conn.close();

			Gson gson = new Gson(); // stak-overflow 사이트 gson 검색
			String json = gson.toJson(list); // json 포맷으로 만듬
			return json; // json view-ajax로 넘김
		}

	}

}
