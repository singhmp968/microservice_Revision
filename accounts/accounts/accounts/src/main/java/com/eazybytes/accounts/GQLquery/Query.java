package com.eazybytes.accounts.GQLquery;

import com.eazybytes.accounts.GraphQlModalRequest.SampleRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query {
    @QueryMapping
    public String hello() {
        return "Hello GQL";
    }
    @QueryMapping
    public String paramsRevise(@Argument String fName, @Argument String lName) {

        return fName + " " + lName;
    }
    @QueryMapping
    public String sampleRequestJson(@Argument SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }
}
