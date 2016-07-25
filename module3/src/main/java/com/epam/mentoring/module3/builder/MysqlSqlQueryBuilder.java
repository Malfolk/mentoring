package com.epam.module2.builder;

/**
 * @author Siarhei_Karytka
 */
public class MysqlSqlQueryBuilder extends SqlQueryBuilder
{
	public SqlQueryBuilder limit(int limit)
	{
		limitBuilder.append("LIMIT ");
		limitBuilder.append(limit);
		limitBuilder.append(";");

		return this;
	}
}
