    protected void ensureIndexes() throws MongoException
    {
        DBObject idKey = BasicDBObjectBuilder.start().add("id", 1).get();        
        _dbSessions.createIndex(idKey,
                              BasicDBObjectBuilder.start()
                              .add("name", "id_1")
                              .add("ns", _dbSessions.getFullName())
                              .add("sparse", false)
                              .add("unique", true)
                              .get());

        DBObject versionKey = BasicDBObjectBuilder.start().add("id", 1).add("version", 1).get();       
        _dbSessions.createIndex(versionKey, BasicDBObjectBuilder.start()
                              .add("name", "id_1_version_1")
                              .add("ns", _dbSessions.getFullName())
                              .add("sparse", false)
                              .add("unique", true)
                              .get());
        //TODO perhaps index on expiry time?
    }
