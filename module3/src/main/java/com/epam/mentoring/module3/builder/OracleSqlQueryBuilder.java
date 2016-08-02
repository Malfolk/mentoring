package com.epam.mentoring.module3.builder;

/**
 * @author Siarhei_Karytka
 */
public class OracleSqlQueryBuilder extends SqlQueryBuilder
{
	protected void buildLimitStatement()
	{
		stringBuilder.append(" WHERE ROWNUM <= ");
		stringBuilder.append(limit);
	}
}
