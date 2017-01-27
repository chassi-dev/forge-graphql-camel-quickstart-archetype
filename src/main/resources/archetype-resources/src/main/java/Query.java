package com.example;

import graphql.schema.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLObjectType.newObject;

class Query {

	private static HashMap<String, Object> getExample(int id){
		HashMap<String, Object> example = new HashMap<>();
		if(id < 1) return example;
		example.put("id", id);
		example.put("text", "example " + id);
		return example;
	}

	private static List getExamples(){
		ArrayList<HashMap<String, Object>> examples = new ArrayList<>();
		for(int i = 1; i < 10; i++) examples.add(getExample(i));
		return examples;
	}

	private static DataFetcher exampleFetcher = (dataFetchingEnvironment) -> getExample(dataFetchingEnvironment.getArgument("id"));


	private static DataFetcher examplesFetcher = (dataFetchingEnvironment -> getExamples());

	private static GraphQLObjectType exampleType = newObject()
					.name("Example")
					.description("Just an example")
					.field((field) -> field.name("id").type(new GraphQLNonNull(GraphQLInt)))
					.field((field) -> field.name("text").type(GraphQLString))
					.build();


	private static GraphQLObjectType queryType = newObject()
					.name("QueryType")
					.description("An example query type")
					.field((field) ->
									field.name("example")
													.argument((argument) -> argument.name("id").type(GraphQLInt))
													.type(exampleType)
													.dataFetcher(exampleFetcher)
					)
					.field((field) ->
									field.name("examples")
													.type(new GraphQLList(exampleType))
													.dataFetcher(examplesFetcher)
					)
					.build();


	public static GraphQLSchema querySchema = GraphQLSchema.newSchema()
					.query(queryType)

					// no mutation created, but here for example on how to add
					//.mutation(mutationType)

					.build();
}
