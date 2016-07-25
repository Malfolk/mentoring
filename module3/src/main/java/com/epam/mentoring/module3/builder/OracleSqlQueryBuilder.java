package com.epam.module2.builder;

/**
 * @author Siarhei_Karytka
 */
public class OracleSqlQueryBuilder extends SqlQueryBuilder
{
	public SqlQueryBuilder limit(int limit)
	{
		limitBuilder.append("WHERE ROWNUM <= ");
		limitBuilder.append(limit);
		limitBuilder.append(";");

		return this;
	}
}
