package com.graphqltest.lab2.config;

import com.graphqltest.lab2.mutation.Mutation;
import com.graphqltest.lab2.repository.DogRepository;
import com.graphqltest.lab2.resolver.Query;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public GraphQLSchema makeSchema(DogRepository dogRepository) {
        GraphQLSchema schema = SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(new Query(dogRepository), new Mutation(dogRepository))
                .build().makeExecutableSchema();

        System.out.println(schema);
        return schema;
    }
}
