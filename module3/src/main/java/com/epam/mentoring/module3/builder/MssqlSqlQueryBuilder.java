package com.epam.module2.builder;

/**
 * @author Siarhei_Karytka
 */
public class MssqlSqlQueryBuilder extends SqlQueryBuilder
{
	public SqlQueryBuilder limit(int limit)
	{
		limitBuilder.append("TOP ");
		limitBuilder.append(limit);

		return this;
	}

	public String build()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append(limitBuilder.toString());
		builder.append(" ");
		builder.append(outputBuilder.toString());
		builder.append(" ");
		builder.append(fromBuilder.toString());
		builder.append(" ");
		builder.append(joinBuilder.toString());
		builder.append(" ");
		builder.append(groupByBuilder.toString());
		builder.append(" ");
		builder.append(orderByBuilder.toString());
		builder.append(";");
		return  builder.toString();
	}
}
