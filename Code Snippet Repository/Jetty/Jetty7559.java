    public long getSessionExpiry (DBCollection sessions, String id) throws Exception
    {
        assertNotNull(sessions);
        assertNotNull(id);
        
        DBObject o = sessions.findOne(new BasicDBObject(MongoSessionDataStore.__ID,id));
        assertNotNull(o);
        Long expiry = (Long)o.get(MongoSessionDataStore.__EXPIRY);
        return (expiry == null? null : expiry.longValue());
    }
