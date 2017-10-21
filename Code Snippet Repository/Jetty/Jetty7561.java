    public long getSessionAccessed (DBCollection sessions, String id) throws Exception
    {
        assertNotNull(sessions);
        assertNotNull(id);
        
        DBObject o = sessions.findOne(new BasicDBObject(MongoSessionDataStore.__ID,id));
        assertNotNull(o);
        Long accessed = (Long)o.get(MongoSessionDataStore.__ACCESSED);
        return (accessed == null? null : accessed.longValue());
    }
