package com.chrisfort.uitests.demo.utilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cpfort on 8/29/17.
 */
public class MongoDbSetupUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDbSetupUtil.class);

    public MongoDbFactory mongoDbFactory(String host, String host2, int port, String database,
        String username, String password, MongoClientOptions mongoClientOptions)
        throws UnknownHostException {

        MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(username,
            database, password.toCharArray());

        LOG.debug("Creating MongoCredential: [Username: {}, Database: {}, Password {}]", username,
            database, password.toCharArray());

        if (null == mongoClientOptions) {
            MongoClientOptions.Builder mongoClientOptionsBuilder = new MongoClientOptions.Builder();

            LOG.debug("Setting MongoDB client timeout to 30 seconds");

            mongoClientOptionsBuilder.connectTimeout(30000);

            LOG.debug("Setting MongoDB max connections idle time to 60 seconds");

            mongoClientOptionsBuilder.maxConnectionIdleTime(60000);

            mongoClientOptionsBuilder.socketKeepAlive(false);

            mongoClientOptions = mongoClientOptionsBuilder.build();
        }

        List<ServerAddress> serverList = new ArrayList<>();
        ServerAddress master = new ServerAddress(host, port);
        serverList.add(master);

        if (!host2.equals(host)) {
            ServerAddress slave = new ServerAddress(host2, port);
            serverList.add(slave);
        }

        MongoClient mongoClient = new MongoClient(serverList, Arrays.asList(mongoCredential),
            mongoClientOptions);

        return new SimpleMongoDbFactory(mongoClient, database);
    }

}
