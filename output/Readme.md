## Overview

I want to set up [ebean](https://ebean.io/) ORM for database manangement from a [Paper Minecraft](https://papermc.io/) plugin. I'm able to shade in the ebean dependencies, but creating a query using "io.ebean:ebean-querybean:" throws an error saying that it cannot find an implementation of SpiRawSqlService.

## Environment

Paper Minecraft: paper-1.19.3-367.jar  
Java 18  
Ebean enhancement plugin for IntelliJ (I checked that I have it enabled for this project)  
io.ebean gradle plugin version 13.10.0  
shadowJar gradle plugin version 7.1.2

## The Stacktrace

Everything is fine saving to the Database. Queries without using a querybean work fine as well. The error is thrown when initializing any class containing a reference to a generated querybean.  
The error outputted is printed the latest.log  
`Caused by: java.lang.IllegalStateException: No service implementation found for interface org.example.ebean.io.ebean.service.SpiRawSqlService`  
This tells us that it couldn't find `org.example.ebean.io.ebean.service.SpiRawSqlService`.  
Looking at the decompiled shadowJar after package relocation, the implementation for this class is found at `org.example.ebean.io.ebeaninternal.server.rawsql.DRawSql`;

Printing out the ClassLoader#getDefinedPackages on the instance supplied when creating the ebean Database connection results in this:

```
org.example.ebean
org.example.ebean.database
org.example.ebean.io.ebean
org.example.ebean.io.ebean.annotation
org.example.ebean.io.ebean.config
org.example.ebean.io.ebean.config.dbplatform
org.example.ebean.io.ebean.datasource
org.example.ebean.io.ebean.meta
```

As you can see, the `org.example.ebean.io.ebeaninternal` package and subpackages are not outputted in this list. How/where is the package "ebeaninternal" being loaded if at all? How can I get the enhanced querybean to find this package so it can load the implementation (DRawSql) of SpiRawSqlService
