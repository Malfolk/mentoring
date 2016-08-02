package com.epam.mentoring.module3.builder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epam.mentoring.module3.builder.statementholders.Join;
import com.epam.mentoring.module3.builder.statementholders.Output;
import com.epam.mentoring.module3.builder.statementholders.TableColumn;

/**
 * @author Siarhei_Karytka
 */
public abstract class SqlQueryBuilder
{
	protected StringBuilder stringBuilder = new StringBuilder();
	private Map<String, String> tableAliasMap = new HashMap<String, String>();

	protected String fromTable;
	protected int limit;
	protected List<Join> joinList = new LinkedList<Join>();
	protected List<Output> outputList = new LinkedList<Output>();
	protected List<TableColumn> groupByList = new LinkedList<TableColumn>();
	protected List<TableColumn> orderByList = new LinkedList<TableColumn>();

	protected abstract void buildLimitStatement();

	public enum Aggregate
	{
		SUM
	}

	public enum JOINS
	{
		INNER_JOIN,
		LEFT_JOIN
	}

	public SqlQueryBuilder from(String tableName)
	{
		this.fromTable = tableName;
		return this;
	}

	public SqlQueryBuilder join(SqlQueryBuilder.JOINS joinType, String leftTableName, String leftTableColumnName, String rightTableName,
			String rightTableColumnName)
	{
		TableColumn leftTableColumn = new TableColumn(leftTableName, leftTableColumnName);
		TableColumn rightTableColumn = new TableColumn(rightTableName, rightTableColumnName);
		Join join = new Join(joinType, leftTableColumn, rightTableColumn);
		joinList.add(join);

		return this;
	}

	public SqlQueryBuilder output(String tableName, String columnName)
	{
		output(tableName, columnName, null);

		return this;
	}

	public SqlQueryBuilder output(String tableName, String columnName, Aggregate aggregateType)
	{
		TableColumn tableColumn = new TableColumn(tableName, columnName);
		Output output = new Output(aggregateType, tableColumn);
		outputList.add(output);

		return this;
	}

	public SqlQueryBuilder groupBy(String tableName, String columnName)
	{
		TableColumn tableColumn = new TableColumn(tableName, columnName);
		groupByList.add(tableColumn);

		return this;
	}

	public SqlQueryBuilder orderBy(String tableName, String columnName)
	{
		TableColumn tableColumn = new TableColumn(tableName, columnName);
		orderByList.add(tableColumn);

		return this;
	}

	public SqlQueryBuilder limit(int limit)
	{
		this.limit = limit;

		return this;
	}

	public String build() throws Exception
	{
		stringBuilder.append("SELECT");
		buildOutputStatement();
		buildFromStatement();
		buildJoinStatement();
		buildGroupByStatement();
		buildOrderByStatement();
		buildLimitStatement();
		stringBuilder.append(";");
		return  stringBuilder.toString();
	}

	protected void buildFromStatement()
	{
		stringBuilder.append(" FROM ");
		stringBuilder.append(fromTable);
		stringBuilder.append(" ");
		stringBuilder.append(getTableAlias(fromTable));
	}

	protected void buildOutputStatement()
	{
		for(Output output : outputList){
			appendOutputColumn(output, isLast(output, outputList));
		}
	}

	protected void buildJoinStatement() throws Exception
	{
		for(Join join : joinList)
		{
			appendJoinStatement(join);
		}
	}

	protected void buildGroupByStatement()
	{
		if(groupByList.size() > 0)
		{
			stringBuilder.append(" GROUP BY");
		}
		for(TableColumn tableColumn : groupByList)
		{
			appendTableAliasAndColumnStatement(tableColumn, isLast(tableColumn, groupByList));
		}
	}

	protected void buildOrderByStatement()
	{
		if(orderByList.size() > 0)
		{
			stringBuilder.append(" ORDER BY");
		}
		for(TableColumn tableColumn : orderByList)
		{
			appendTableAliasAndColumnStatement(tableColumn, isLast(tableColumn, orderByList));
		}
	}

	private void appendOutputColumn(Output output, boolean isLast)
	{
		SqlQueryBuilder.Aggregate aggregateType = output.getAggregateType();
		if( aggregateType != null )
		{
			wrapWithAggregateAndAppend(aggregateType, output.getTableColumn());
		}
		else
		{
			appendTableAliasAndColumnStatement(output.getTableColumn(), true);
		}
		if(!isLast)
		{
			stringBuilder.append(",");
		}
	}

	private void appendTableAliasAndColumnStatement(TableColumn tableColumn, boolean isLast)
	{
		stringBuilder.append(" ");
		stringBuilder.append(getTableAlias(tableColumn.getTableName()));
		stringBuilder.append(".");
		stringBuilder.append(tableColumn.getColumnName());
		if(!isLast)
		{
			stringBuilder.append(",");
		}
	}

	private void appendJoinStatement(Join join) throws Exception
	{
		stringBuilder.append(" ");
		switch (join.getJoinType())
		{
			case INNER_JOIN:
				stringBuilder.append("JOIN");
				break;
			case LEFT_JOIN:
				stringBuilder.append("LEFT JOIN");
				break;
			default:
				throw new Exception("Unsupported JOIN type");
		}
		stringBuilder.append(" ");
		appendTableWithAliasStatement(join.getRightTableColumn());
		stringBuilder.append(" ON");
		appendTableAliasAndColumnStatement(join.getLeftTableColumn(), true);
		stringBuilder.append(" =");
		appendTableAliasAndColumnStatement(join.getRightTableColumn(), true);

	}

	private void appendTableWithAliasStatement(TableColumn tableColumn)
	{
		stringBuilder.append(tableColumn.getTableName());
		stringBuilder.append(" ");
		stringBuilder.append(getTableAlias(tableColumn.getTableName()));
	}

	private void wrapWithAggregateAndAppend(Aggregate aggregateType, TableColumn tableColumn)
	{
		switch (aggregateType)
		{
			case SUM:
				stringBuilder.append(" SUM");
				stringBuilder.append("(");
				stringBuilder.append(getTableAlias(tableColumn.getTableName()));
				stringBuilder.append(".");
				stringBuilder.append(tableColumn.getColumnName());
				stringBuilder.append(")");
		}
	}

	private boolean isLast(Object object, List list)
	{
		return list.indexOf(object) == (list.size() -1);
	}

	private String getTableAlias(String tableName)
	{
		for(int i = 1; i <= tableName.length(); i++)
		{
			String alias = tableName.substring(0, i);
			String existingTableName = tableAliasMap.get(alias);
			if( existingTableName != null)
			{
				if(existingTableName.equals(tableName))
				{
					return alias;
				}
			}
			else
			{
				tableAliasMap.put(alias, tableName);
				return alias;
			}

		}
		return null;
	}
}
