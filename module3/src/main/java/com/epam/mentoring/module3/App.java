package com.epam.mentoring.module3;

import com.epam.mentoring.module3.builder.SqlQueryBuilder;
import com.epam.mentoring.module3.factory.SQLQueryBuilderFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SqlQueryBuilder builder = SQLQueryBuilderFactory.createSqlQueryBuilder(SQLQueryBuilderFactory.DB.ORACLE);
        String sqlQuery = builder.from("Order")
                .join(SqlQueryBuilder.JOINS.INNER_JOIN , "Order", "CustomerId", "Customer", "Id")
                .join(SqlQueryBuilder.JOINS.LEFT_JOIN , "Customer", "CityId", "City", "Id")
                .output("Order", "TotalPrice", SqlQueryBuilder.Aggregate.SUM) // indicates that "sum" function must be applied
                .output("Client", "FirstName")
                .output("Client", "LastName")
                .output("City", "Name")
                .groupBy("Client", "FirstName")
                .groupBy("Client", "LastName")
                .groupBy("City", "Name")
                .orderBy("Client", "LastName")
                .limit(5).build();

        System.out.println(sqlQuery);
    }
}
