package com.epam.mentoring.module3.builder;

/**
 * @author Siarhei_Karytka
 */
public class MysqlSqlQueryBuilder extends SqlQueryBuilder
{
	protected void buildLimitStatement()
	{
		stringBuilder.append(" LIMIT ");
		stringBuilder.append(limit);
	}
}
