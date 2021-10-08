package com.alirismaurera.heroisapi.config;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.alirismaurera.heroisapi.constans.HeroesContant.ENDPOINT_DYNAMO;
import static com.alirismaurera.heroisapi.constans.HeroesContant.REGIAO_DYNAMO;



public class HeroesData {

    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGIAO_DYNAMO))
                .build();

        DynamoDB dynamo = new DynamoDB(client);

        Table table = dynamo.getTable("Heroes_Table");
        Item hero = new Item()
        .withPrimaryKey("id", "1")
                .withString("name", "Mulher Maravilha")
                .withString("universe", "comics")
                .withNumber("films", 3);

        Item hero2 = new Item()
                .withPrimaryKey("id", "3")
                .withString("name", "Viuva negra")
                .withString("universe", "marvel")
                .withNumber("films", 2);

        Item hero3 = new Item()
                .withPrimaryKey("id", "4")
                .withString("name", "Capita marvel")
                .withString("universe", "marvel")
                .withNumber("films", 2);

        PutItemOutcome outcome = table.putItem(hero);
        PutItemOutcome outcome2 = table.putItem(hero2);
        PutItemOutcome outcome3 = table.putItem(hero3);


    }
}
