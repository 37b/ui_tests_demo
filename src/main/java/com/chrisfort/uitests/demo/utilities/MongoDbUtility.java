package com.chrisfort.uitests.demo.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;

/**
 * Created by cpfort on 8/29/17.
 */
public abstract class MongoDbUtility {

    protected MongoTemplate mongoTemplate;

    Logger LOG = LoggerFactory.getLogger(MongoDbUtility.class);

    public MongoDbUtility(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    protected <T> T findById(String id, Class<T> entityClass)  throws UnknownHostException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        LOG.info("Searching for record with ID [{}].", id);

        return mongoTemplate.findOne(query, entityClass);
    }

}
