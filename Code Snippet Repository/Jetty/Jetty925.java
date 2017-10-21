    protected Entity entityFromSession (SessionData session, Key key) throws Exception
    {
        if (session == null)
            return null;
        
        Entity entity = null;
        
        //serialize the attribute map
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(session.getAllAttributes());
        oos.flush();
        
        //turn a session into an entity         
        entity = Entity.newBuilder(key)
                .set(_model.getId(), session.getId())
                .set(_model.getContextPath(), session.getContextPath())
                .set(_model.getVhost(), session.getVhost())
                .set(_model.getAccessed(), session.getAccessed())
                .set(_model.getLastAccessed(), session.getLastAccessed())
                .set(_model.getCreateTime(), session.getCreated())
                .set(_model.getCookieSetTime(), session.getCookieSet())
                .set(_model.getLastNode(),session.getLastNode())
                .set(_model.getExpiry(), session.getExpiry())
                .set(_model.getMaxInactive(), session.getMaxInactiveMs())
                .set(_model.getLastSaved(), session.getLastSaved())
                .set(_model.getAttributes(), BlobValue.newBuilder(Blob.copyFrom(baos.toByteArray())).setExcludeFromIndexes(true).build()).build();

                 
        return entity;
    }
