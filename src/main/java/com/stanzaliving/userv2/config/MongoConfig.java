package com.stanzaliving.userv2.config;

import com.mongodb.*;
import com.stanzaliving.userv2.constants.MongoConstants;
import com.stanzaliving.userv2.listeners.MongoEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.stanzaliving"})
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.readPreference}")
    private String readPreference;

    @Value("${mongodb.writeConcern.w}")
    private String writeConcernW;

    @Value("${mongodb.writeConcern.j}")
    private Boolean writeConcernJ;

    @Value("${mongodb.writeConcern.wtimeout}")
    private Long writeConcernTimeOut;

    @Value("${mongodb.readConcernLevel}")
    private String readConcernLevel;

    @Value("${mongodb.maxPoolSize}")
    private int maxPoolSize;

    @Value("${mongodb.minPoolSize}")
    private int minPoolSize;

    @Value("${mongodb.maxIdleTimeMS}")
    private int maxIdleTimeMS;

    @Value("${mongodb.servers}")
    private String servers;

    @Value("${mongodb.user}")
    private String username;

    @Value("${mongodb.password}")
    private String password;

    @Value("${mongodb.databaseName}")
    private String database;

    @Value("${mongodb.authDatabase}")
    private String authDatabase;

    private static final String DEFAULT = "default";

    @Override
    public MongoClient mongoClient() {

        ReadPreference readPref = (readPreference == null) ? MongoConstants.readPref.get(DEFAULT) : MongoConstants.readPref.get(readPreference.trim().toLowerCase());
        if (readPref == null) MongoConstants.readPref.get(DEFAULT);

        ReadConcern readConcern = (readConcernLevel == null) ? MongoConstants.readConcern.get(DEFAULT) : MongoConstants.readConcern.get(readConcernLevel);
        if (readConcern == null) MongoConstants.readConcern.get(DEFAULT);

        WriteConcern writeConcern;
        if (writeConcernJ == null && writeConcernTimeOut == null && writeConcernW == null)
            writeConcern = MongoConstants.DEFAULT_WRITE_CONCERN;
        else {
            if (writeConcernW.trim().toLowerCase().equalsIgnoreCase("majority"))
                writeConcern = WriteConcern.MAJORITY;
            else {
                writeConcern = new WriteConcern(Integer.parseInt(writeConcernW));
            }
        }
        writeConcern.withJournal(writeConcernJ == null ? MongoConstants.DEFAULT_WRITECONCERN_J : writeConcernJ).
                withWTimeout(writeConcernTimeOut == null ? MongoConstants.DEFAULT_WRITECONCERN_WTIMEOUT : writeConcernTimeOut, TimeUnit.MILLISECONDS);


        MongoClientOptions options = MongoClientOptions.builder().
                readPreference(readPref).
                writeConcern(writeConcern).
                readConcern(readConcern).
                connectionsPerHost(maxPoolSize == 0 ? MongoConstants.DEFAULT_MAX_POOL_SIZE : maxPoolSize).
                minConnectionsPerHost((minPoolSize == 0 || minPoolSize > maxPoolSize) ? MongoConstants.DEFAULT_MIN_POOL_SIZE : minPoolSize).
                maxConnectionIdleTime(maxIdleTimeMS == 0 ? MongoConstants.DEFAULT_MAX_IDLE_TIMEMS : maxIdleTimeMS).
                build();

        String[] serverArr = servers.split(",");
        List<ServerAddress> address = new LinkedList<>();


        for (String s : serverArr) {
            address.add(new ServerAddress(s.split(":")[0], Integer.parseInt(s.split(":")[1])));
        }

        MongoCredential credential = MongoCredential.createCredential(username, authDatabase == null ? database : authDatabase, password.toCharArray());

        MongoClientURI mongoClientURI = new MongoClientURI(uri);

        return new MongoClient(mongoClientURI);

        //return new MongoClient(address, credential, options);

    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate template = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
        WriteConcern writeConcern = WriteConcern.MAJORITY;
        writeConcern.withJournal(true);
        writeConcern.withWTimeout(writeConcernTimeOut, TimeUnit.MILLISECONDS);
        template.setWriteConcern(writeConcern);
        template.setReadPreference(ReadPreference.primary());
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
    }

    @Bean
    public MongoEventListener mongoEventListener() {
        return new MongoEventListener();
    }


    //Method checks whether the dots are present in a Map or not.
    // If found, then it should be replaced with the character specified.
    @Autowired
    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setMapKeyDotReplacement("_");
    }
}
