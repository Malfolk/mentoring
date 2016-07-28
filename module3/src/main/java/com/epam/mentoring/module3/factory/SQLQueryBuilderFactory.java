package com.epam.mentoring.module3.factory;

import com.epam.mentoring.module3.builder.MssqlSqlQueryBuilder;
import com.epam.mentoring.module3.builder.MysqlSqlQueryBuilder;
import com.epam.mentoring.module3.builder.OracleSqlQueryBuilder;
import com.epam.mentoring.module3.builder.SqlQueryBuilder;

/**
 * @author Siarhei_Karytka
 */
public class SQLQueryBuilderFactory
{
	public enum DB
	{
		MYSQL,
		MSSQL,
		ORACLE
	}

	public static SqlQueryBuilder createSqlQueryBuilder(DB dbProvider) throws Exception
	{
		switch (dbProvider)
		{
			case MSSQL:
				return new MssqlSqlQueryBuilder();
			case MYSQL:
				return new MysqlSqlQueryBuilder();
			case ORACLE:
				return new OracleSqlQueryBuilder();
			default:
				throw new Exception("Unsupported DB provider");
		}
	}
}
