    public long getSessionMaxInactiveInterval (DBCollection sessions, String id) throws Exception
    {
        assertNotNull(sessions);
        assertNotNull(id);
        
        DBObject o = sessions.findOne(new BasicDBObject(MongoSessionDataStore.__ID,id));
        assertNotNull(o);
        Long inactiveInterval = (Long)o.get(MongoSessionDataStore.__MAX_IDLE);
        return (inactiveInterval == null? null : inactiveInterval.longValue());
    }
