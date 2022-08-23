package com.stanzaliving.userv2.constants;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MongoConstants {

    public static final Map<String, ReadPreference> readPref;
    public static final String DEFAULT_WRITECONCERN_W = "majority";
    public static final boolean DEFAULT_WRITECONCERN_J = true;
    public static final long DEFAULT_WRITECONCERN_WTIMEOUT = 2000;
    public static final WriteConcern DEFAULT_WRITE_CONCERN = WriteConcern.MAJORITY.withJournal(true).withWTimeout(2000, TimeUnit.MILLISECONDS);
    public static final Map<String, ReadConcern> readConcern;
    public static final int DEFAULT_MAX_POOL_SIZE = 16;
    public static final int DEFAULT_MIN_POOL_SIZE = 4;
    public static final int DEFAULT_MAX_IDLE_TIMEMS = 30000;

    static {
        HashMap<String, ReadPreference> tmp = new HashMap<>();
        tmp.put("primary", ReadPreference.primary());
        tmp.put("primary_preferred", ReadPreference.primaryPreferred());
        tmp.put("secondary", ReadPreference.secondary());
        tmp.put("secondary_preferred", ReadPreference.secondaryPreferred());
        tmp.put("default", ReadPreference.primary());
        readPref = Collections.unmodifiableMap(tmp);
    }

    static {
        HashMap<String, ReadConcern> tmp = new HashMap<>();
        tmp.put(DEFAULT_WRITECONCERN_W, ReadConcern.MAJORITY);
        tmp.put("linearizable", ReadConcern.LINEARIZABLE);
        tmp.put("snapshot", ReadConcern.SNAPSHOT);
        tmp.put("local", ReadConcern.LOCAL);
        tmp.put("default", ReadConcern.MAJORITY);
        readConcern = Collections.unmodifiableMap(tmp);
    }


}
