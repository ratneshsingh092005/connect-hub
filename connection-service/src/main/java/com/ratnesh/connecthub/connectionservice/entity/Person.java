package com.ratnesh.connecthub.connectionservice.entity;

import org.springframework.data.neo4j.core.schema.Node;

@Node

public class Person {

    private Long userId;

    private String name;
}
