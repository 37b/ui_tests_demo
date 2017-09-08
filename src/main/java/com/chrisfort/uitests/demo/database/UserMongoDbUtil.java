package com.chrisfort.uitests.demo.database;

import com.chrisfort.uitests.demo.database.model.UserRecord;
import com.chrisfort.uitests.demo.utilities.MongoDbUtility;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by cpfort on 8/29/17.
 */
public class UserMongoDbUtil extends MongoDbUtility{

    public UserMongoDbUtil (MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public UserRecord findById(String id) throws UnknownHostException{
        return findById(id);
    }

    /**
     * Returns all user records with the last name provided
     * @param lastName The last name to search for
     * @return A list of Users
     * @throws UnknownHostException
     */
    public List<UserRecord> findAllByLastName(String lastName) throws UnknownHostException {

        Query query = new Query();

        query.addCriteria(Criteria.where("lastName").is(lastName));

        return mongoTemplate.find(query, UserRecord.class);
    }

}
