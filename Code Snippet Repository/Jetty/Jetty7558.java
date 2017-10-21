    public void verifySessionTimeout (DBCollection sessions, String id, int sec) throws Exception
    {
        long val;
        
        if (sec > 0)
            val = sec*1000L;
        else
            val = sec;
        
        assertNotNull(sessions);
        assertNotNull(id);
        
        DBObject o = sessions.findOne(new BasicDBObject(MongoSessionDataStore.__ID,id));
        assertNotNull(o);
        Long maxIdle = (Long)o.get(MongoSessionDataStore.__MAX_IDLE);
        assertNotNull(maxIdle);
        assertEquals(val, maxIdle.longValue());
    }
