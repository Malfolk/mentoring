package com.epam.mentoring.module3.builder.statementholders;

/**
 * @author Siarhei_Karytka
 */
public class TableColumn
{
	private String tableName;
	private String columnName;

	public TableColumn(String tableName, String columnName)
	{
		this.tableName = tableName;
		this.columnName = columnName;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
}
