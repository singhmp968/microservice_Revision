package com.eazybytes.accounts.GQLquery;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query {
    @QueryMapping
    public String hello() {
        return "Hello GQL";
    }
}
