package com.epam.mentoring.module3.builder;

/**
 * @author Siarhei_Karytka
 */
public class MssqlSqlQueryBuilder extends SqlQueryBuilder
{
	protected void buildLimitStatement()
	{
		stringBuilder.append(" TOP ");
		stringBuilder.append(limit);
	}

	public String build() throws Exception
	{
		stringBuilder.append("SELECT");
		buildLimitStatement();
		buildOutputStatement();
		buildFromStatement();
		buildJoinStatement();
		buildGroupByStatement();
		buildOrderByStatement();
		stringBuilder.append(";");
		return  stringBuilder.toString();
	}
}
