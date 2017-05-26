package first.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("sample.SelectBoardList", map);
	}

	public void insertBoard(Map<String, Object> map) {
		insert("sample.insertBoard", map);
	}

}
