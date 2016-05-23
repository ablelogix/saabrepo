package com.ablelogix.maven.MavenDataDriven.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReadDB implements JDBCConstants {

	//tablename
		public String tableName=null;
		public Connection myCon = null;
		public Statement stmt = null;
		
		//consructor
		public ReadDB(String tableName){
			this.tableName = tableName;
			
			try {
				Class.forName(JDBCConstants.dbDriver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				myCon = DriverManager.getConnection(JDBCConstants.dbURL, JDBCConstants.username, JDBCConstants.pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(myCon==null){
				try {
					throw new Connection2DBException();
				} catch (Connection2DBException e) {
					// TODO Auto-gen	erated catch block
					System.out.println(e.getMessage());
				}
			}
			
		}

		
		public boolean retrieveToRunFlag(String tableName, String toRun,String testSuite)
		{
			boolean flag = false;
			String suite2run = null;
			
			String sqlStr = "select "+toRun+ " from "+tableName+ " where suitename= '"+testSuite+"'";

			try {
				stmt = myCon.createStatement();
				ResultSet rs = stmt.executeQuery(sqlStr);
				
				while(rs.next()){
					
					suite2run = rs.getString(toRun);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(suite2run.equals("Y")){
				flag = true;
			}else{
				flag = false;
			}
			
			return flag;
		}
		
	
		public int getColCount(){
			int colCount = 0;
			String sqlStr = "select * from "+this.tableName;
			try {
				stmt = myCon.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ResultSet rs = stmt.executeQuery(sqlStr);
				ResultSetMetaData rsmd = rs.getMetaData();
				colCount = rsmd.getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return colCount;
		}
		
		public int getRowCount(){
			String sqlStr = "select count(*) from "+this.tableName;
			int rowCount = 0;
			try {
				stmt = myCon.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ResultSet rs = stmt.executeQuery(sqlStr);
				while(rs.next()){
					rowCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rowCount;
		}
		
		public ArrayList <Object[]> readCompleteData(String tablename,int colCount){
			ArrayList <Object[]> result = new ArrayList();
			String selectStr = "select * from "+this.tableName;
			try {
				stmt = myCon.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ResultSet rs = stmt.executeQuery(selectStr);
				while(rs.next()){
					Object [] record = new Object[colCount];
					for(int i=0;i<record.length;i++){
						record[i] = rs.getObject(i+1);
					}
					result.add(record);				
				}
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;

		}
		
		public boolean writeResult(String tableName, String ColName,String rowName,String Result){
			boolean flag = false;
			if(ColName.equals("skipped_executed")){
				String updateStr = "update " + this.tableName + " set "+ ColName + "= '"+Result+"' where suitename='"+rowName+"'";
				System.out.println("inside UPDATE ........................"+updateStr);
				try {
					stmt = myCon.createStatement();
					int pass_fail = stmt.executeUpdate(updateStr);
					
					if(pass_fail==1){
						System.out.println("updated successfully");
						flag = true;
					}else{
						System.out.println("NOT updated successfully");
						flag = false;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
			return flag;
		}
		
		
		//check whther test case is marked has
		//Y then run the test case
		//anything other Y mark testcase
		//has SKIPPED
		
		public boolean retrieveToRunTestCaseFlag(String tableName, String testcaseRunFlag,String testcaseName){
			boolean flag = false;
			String case2run = null;
			String sqlStr = "select " + testcaseRunFlag + " from " + tableName + " where TESTCASENAME ='"+testcaseName+"'";
			try {
				stmt = myCon.createStatement();
				ResultSet rs = stmt.executeQuery(sqlStr);
				while(rs.next()){
					case2run = rs.getString(testcaseRunFlag);
				}
				
				if(case2run.equalsIgnoreCase("y")){
					flag = true;
				}else{
					flag = false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}//end of method
		
		
		//write the testcase skip_fail_pass to skipped or passed or failed
		public void writeTestcaseResult(String tableName, String ColName,String testcaseName,String Result){
			
			try {
				stmt = myCon.createStatement();
				String updateStr = "update "+tableName+" set "+ColName+" = '"+Result+"' where TESTCASENAME ='"+testcaseName+"'";
				int success_fail = stmt.executeUpdate(updateStr);
				
				if(success_fail==1){
					System.out.println("updated successfully testcase table name");
				}else{
					System.out.println("not updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end of method
		
		//get all the data2run flags in the loginsuiteadmin
		public ArrayList retrieveToRunFlagTestData(String tableName,String colName){
			ArrayList <String> data2runList = new ArrayList<String>();
			try {
				stmt = myCon.createStatement();
				String sqlStr = "select "+ colName + " from "+tableName;
				ResultSet rs = stmt.executeQuery(sqlStr);
				while(rs.next()){
					data2runList.add(rs.getString(colName));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return data2runList;
		}//end of method
		
		
		
		public void displayTestData(ArrayList <Object[]> arrayListData){
	
			for(int i=0;i<arrayListData.size();i++){
				Object[] dataObjects = arrayListData.get(i);
				for(int j=0;j<dataObjects.length;j++){
					System.out.println("data is ******"+dataObjects[j]);
				}
				
			}
		}
		
		public Object[][] convert2Array(ArrayList<Object[]> result,int rowCount,int colCount){
			Object [][] dataArray = new Object[rowCount][colCount];
			for(int i=0;i<result.size();i++){
				Object[] rowdata = result.get(i);
				for(int j=0;j<rowdata.length;j++){
					Object data = rowdata[j];
					dataArray[i][j] = data; 
				}
			}
			return dataArray;
		}
		
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tableName = "ABLELOGIX_SUITESLIST";
		/*ReadDB dbObj = new ReadDB(tableName);
		boolean flag = dbObj.retrieveToRunFlag(tableName, "suitetorun","LoginSuite");
		System.out.println("flag is ...."+flag);
		*/
		String tableNameAnother = "departments";
		ReadDB dbObj1 = new ReadDB(tableNameAnother);
		System.out.println("no of columns is ......"+dbObj1.getColCount());
		
		ArrayList <Object []> dispdata =dbObj1.readCompleteData(tableName, dbObj1.getColCount());
		dbObj1.displayTestData(dispdata);
	}


	public void writeTestDataResult(String tableName, String colName,
			int rowNum, String result) {
		// TODO Auto-generated method stub
		if(colName.equals("pass_fail_skip")){
			//update loginsuiteadmin set pass_fail_skip = 'pass/fail test data'
			//where serialno=1

			String updateStr = "update " + tableName + " set "+ colName +"="+"'"+result+"'"+ " where serialno="+rowNum;
			System.out.println("update String *******"+updateStr);
			try {
				stmt = myCon.createStatement();
				int status = stmt.executeUpdate(updateStr);
				
				if(status ==1){
					System.out.println("updated succesfully");
				}
				else{
					System.out.println("Not updated succesfully");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}

		
		
	}

}
