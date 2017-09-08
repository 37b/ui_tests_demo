package com.chrisfort.uitests.demo.CONFIG;

import com.chrisfort.uitests.demo.database.UserMongoDbUtil;
import com.chrisfort.uitests.demo.utilities.MongoDbSetupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Created by cpfort on 8/29/17.
 */
@Configuration
public class UserRecordsDbConfiguration {

    private String mongoDbHost;

    @Autowired
    private MongoDbSetupUtil mongoDbSetupUtil;

    @Value("mongoDb.userDb.host")
    private String host;

    @Value("mongoDb.userDb.host")
    private String host2;

    @Value("mongoDb.userDb.port")
    private int port;

    @Value("mongoDb.userDb.username")
    private String username;

    @Value("mongoDb.userDb.password")
    private String password;

    @Value("mongoDb.userDb.database")
    private String database;

    @Value("mongoDb.userDb.collection")
    private String collection;

    @Bean
    public UserMongoDbUtil userMongoDbUtil(
        @Qualifier("userMongoTemplate")
            MongoTemplate userMongoTemplate) throws UnknownHostException {
        return new UserMongoDbUtil(userMongoTemplate);
    }

    @Bean
    public MongoTemplate userMongoTemplate() throws UnknownHostException {
        MongoDbFactory mongoDbFactory = mongoDbSetupUtil.mongoDbFactory(host, host2, port, database,
            username, password, null);
        return new MongoTemplate(mongoDbFactory);
    }
}
