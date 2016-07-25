package com.epam.module2.builder;

import java.util.HashMap;
import java.util.Map;

import com.epam.module2.factory.SQLQueryBuilderFactory;

/**
 * @author Siarhei_Karytka
 */
public abstract class SqlQueryBuilder
{
	protected StringBuilder fromBuilder = new StringBuilder();
	protected StringBuilder joinBuilder = new StringBuilder();
	protected StringBuilder outputBuilder = new StringBuilder();
	protected StringBuilder groupByBuilder = new StringBuilder();
	protected StringBuilder orderByBuilder = new StringBuilder();
	protected StringBuilder limitBuilder = new StringBuilder();
	private Map<String, String> tableAliasMap = new HashMap<String, String>();
	private String lastOperation;
	private String currentOperation;

	public abstract SqlQueryBuilder limit(int limit);

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
		fromBuilder.append("FROM ");
		fromBuilder.append(tableName);
		return this;
	}

	public SqlQueryBuilder join(SqlQueryBuilder.JOINS joinType, String leftTable, String leftTableID, String rightTable,
			String rightTableID) throws Exception
	{
		currentOperation = "join";
		if(currentOperation.equals(lastOperation))
		{
			joinBuilder.append(" ");
		}
		switch (joinType)
		{
			case INNER_JOIN:
				joinBuilder.append("JOIN");
				break;
			case LEFT_JOIN:
				joinBuilder.append("LEFT JOIN");
				break;
			default:
				throw new Exception("Unsupported JOIN type");
		}
		joinBuilder.append(" ");
		joinBuilder.append(rightTable);
		joinBuilder.append(" ");
		joinBuilder.append(getTableAlias(rightTable));
		joinBuilder.append(" ");
		joinBuilder.append("ON ");
		joinBuilder.append(getTableAlias(leftTable));
		joinBuilder.append(".");
		joinBuilder.append(leftTableID);
		joinBuilder.append(" = ");
		joinBuilder.append(getTableAlias(rightTable));
		joinBuilder.append(".");
		joinBuilder.append(rightTableID);

		lastOperation = currentOperation;
		return this;
	}

	public SqlQueryBuilder output(String tableName, String columnName)
	{
		currentOperation = "output";

		decideToSeparateValues(outputBuilder);
		outputBuilder.append(getTableAlias(tableName));
		outputBuilder.append(".");
		outputBuilder.append(columnName);

		lastOperation = currentOperation;
		return this;
	}

	public SqlQueryBuilder output(String tableName, String columnName, Aggregate aggregateType)
	{
		currentOperation = "output";
		decideToSeparateValues(outputBuilder);
		switch (aggregateType)
		{
			case SUM:
				outputBuilder.append("SUM");
				outputBuilder.append("(");
				outputBuilder.append(getTableAlias(tableName));
				outputBuilder.append(".");
				outputBuilder.append(columnName);
				outputBuilder.append(")");
		}

		lastOperation = currentOperation;
		return this;
	}

	public SqlQueryBuilder groupBy(String tableName, String columnName)
	{
		currentOperation = "groupBy";
		if(!currentOperation.equals(lastOperation))
		{
			groupByBuilder.append("GROUP BY ");
		}
		decideToSeparateValues(groupByBuilder);
		groupByBuilder.append(getTableAlias(tableName));
		groupByBuilder.append(".");
		groupByBuilder.append(columnName);

		lastOperation = currentOperation;
		return this;
	}

	public SqlQueryBuilder orderBy(String tableName, String columnName)
	{
		currentOperation = "orderBy";
		if(!currentOperation.equals(lastOperation))
		{
			orderByBuilder.append("ORDER BY ");
		}
		decideToSeparateValues(orderByBuilder);
		orderByBuilder.append(getTableAlias(tableName));
		orderByBuilder.append(".");
		orderByBuilder.append(columnName);

		lastOperation = currentOperation;
		return this;
	}

	public String build()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append(outputBuilder.toString());
		builder.append(" ");
		builder.append(fromBuilder.toString());
		builder.append(" ");
		builder.append(joinBuilder.toString());
		builder.append(" ");
		builder.append(groupByBuilder.toString());
		builder.append(" ");
		builder.append(orderByBuilder.toString());
		builder.append(" ");
		builder.append(limitBuilder.toString());
		return  builder.toString();
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
				else
				{
					continue;
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

	private void decideToSeparateValues(StringBuilder builder)
	{
		if(currentOperation.equals(lastOperation))
			builder.append(", ");
	}
}
