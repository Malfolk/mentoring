package com.epam.mentoring.module3.builder.statementholders;

import com.epam.mentoring.module3.builder.SqlQueryBuilder;

/**
 * @author Siarhei_Karytka
 */
public class Join
{
	private SqlQueryBuilder.JOINS joinType;
	private TableColumn leftTableColumn;
	private TableColumn rightTableColumn;

	public Join(SqlQueryBuilder.JOINS joinType, TableColumn leftTableColumn, TableColumn rightTableColumn)
	{
		this.joinType = joinType;
		this.leftTableColumn = leftTableColumn;
		this.rightTableColumn = rightTableColumn;
	}

	public SqlQueryBuilder.JOINS getJoinType()
	{
		return joinType;
	}

	public void setJoinType(SqlQueryBuilder.JOINS joinType)
	{
		this.joinType = joinType;
	}

	public TableColumn getLeftTableColumn()
	{
		return leftTableColumn;
	}

	public void setLeftTableColumn(TableColumn leftTableColumn)
	{
		this.leftTableColumn = leftTableColumn;
	}

	public TableColumn getRightTableColumn()
	{
		return rightTableColumn;
	}

	public void setRightTableColumn(TableColumn rightTableColumn)
	{
		this.rightTableColumn = rightTableColumn;
	}
}
