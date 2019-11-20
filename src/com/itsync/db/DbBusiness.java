package com.itsync.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itsync.bean.DataBean;
import com.itsync.bean.NewDataBean;

public class DbBusiness {

	public static void addData(NewDataBean.ResultsBean.AttributesBean at) {

		String addSql = "insert into T_faceRecData (ItemId,Name,TotalNum) values (" + 1 + ",'0-18ÀÍƒÍ¡‰∂Œ',"
				+ at.getStat_age0() + "),(" + 2 + ",'19-28ÀÍƒÍ¡‰∂Œ'," + at.getStat_age1() + "),(" + 3 + ",'29-38ÀÍƒÍ¡‰∂Œ',"
				+ at.getStat_age2() + "),(" + 4 + ",'39-48ÀÍƒÍ¡‰∂Œ'," + at.getStat_age3() + "),(" + 5 + ",'>49ÀÍƒÍ¡‰∂Œ',"
				+ at.getStat_age4() + ")";
		DbHelper dbHelper = new DbHelper(addSql);
		PreparedStatement mPreparedStatement = dbHelper.mPreparedStatement;
		try {
			mPreparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
	}

	public static void deleteAll() {
		String deleteSql = "delete from T_faceRecData";
		DbHelper dbHelper = new DbHelper(deleteSql);
		PreparedStatement mPreparedStatement = dbHelper.mPreparedStatement;
		try {
			mPreparedStatement.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
	}

	public static void addPersonInfo(DataBean dataBean) {
		String sql = "insert into T_faceDataInfo (db_image,name,type,person_id,age,gender,title) values ('"
				+ dataBean.getDb_image() + "','" + dataBean.getName() + "'," + dataBean.getType() + ",'"
				+ dataBean.getPerson_id() + "'," + dataBean.getAge() + ",'" + dataBean.getGender() + "','"
				+ dataBean.getTitle() + "')";
		DbHelper dbHelper = new DbHelper(sql);
		PreparedStatement mPreparedStatement = dbHelper.mPreparedStatement;
		try {
			mPreparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
	}

}
