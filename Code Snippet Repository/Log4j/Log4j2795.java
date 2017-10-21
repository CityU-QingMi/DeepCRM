    public MongoDbConnection(final DB database, final WriteConcern writeConcern, final String collectionName,
            final Boolean isCapped, final Integer collectionSize) {
        if (database.collectionExists(collectionName)) {
            collection = database.getCollection(collectionName);
        } else {
            final BasicDBObject options = new BasicDBObject();
            options.put("capped", isCapped);
            options.put("size", collectionSize);
            this.collection = database.createCollection(collectionName, options);
        }
        this.writeConcern = writeConcern;
    }
