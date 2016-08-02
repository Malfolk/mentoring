package com.epam.mentoring.module3.builder.statementholders;

import com.epam.mentoring.module3.builder.SqlQueryBuilder;

/**
 * @author Siarhei_Karytka
 */
public class Output
{
	private SqlQueryBuilder.Aggregate aggregateType;
	private TableColumn tableColumn;

	public Output(SqlQueryBuilder.Aggregate aggregateType, TableColumn tableColumn)
	{
		this.aggregateType = aggregateType;
		this.tableColumn = tableColumn;
	}

	public SqlQueryBuilder.Aggregate getAggregateType()
	{
		return aggregateType;
	}

	public void setAggregateType(SqlQueryBuilder.Aggregate aggregateType)
	{
		this.aggregateType = aggregateType;
	}

	public TableColumn getTableColumn()
	{
		return tableColumn;
	}

	public void setTableColumn(TableColumn tableColumn)
	{
		this.tableColumn = tableColumn;
	}
}
