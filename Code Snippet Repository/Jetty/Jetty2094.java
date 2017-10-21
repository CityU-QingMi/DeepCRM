    @Override
    public boolean exists(String id) throws Exception
    {
        DBObject fields = new BasicDBObject();
        fields.put(__EXPIRY, 1);
        fields.put(__VALID, 1);
        
        DBObject sessionDocument = _dbSessions.findOne(new BasicDBObject(__ID, id), fields);
        
        if (sessionDocument == null)
            return false; //doesn't exist

        Boolean valid = (Boolean)sessionDocument.get(__VALID);
        if (!valid)
            return false; //invalid - nb should not happen
        
        Long expiry = (Long)sessionDocument.get(__EXPIRY);
        
        if (expiry.longValue() <= 0)
            return true; //never expires, its good
        return (expiry.longValue() > System.currentTimeMillis()); //expires later
    }
