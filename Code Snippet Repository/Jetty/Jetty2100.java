    @Override
    public SessionDataStore getSessionDataStore(SessionHandler handler) throws Exception
    {
        MongoSessionDataStore store = new MongoSessionDataStore();
        store.setGracePeriodSec(getGracePeriodSec());
        store.setSavePeriodSec(getSavePeriodSec());
        Mongo mongo;

        if (!StringUtil.isBlank(getConnectionString()))
            mongo = new Mongo(new MongoURI(getConnectionString()));
        else if (!StringUtil.isBlank(getHost()) && getPort() != -1)
            mongo = new Mongo(getHost(), getPort());
        else if (!StringUtil.isBlank(getHost()))
            mongo = new Mongo(getHost());
        else
            mongo = new Mongo();
        store.setDBCollection(mongo.getDB(getDbName()).getCollection(getCollectionName()));
        return store;
    }
