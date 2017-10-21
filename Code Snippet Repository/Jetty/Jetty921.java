    protected Set<ExpiryInfo> queryExpiryByEntity () throws Exception
    {
        Set<ExpiryInfo> info = new HashSet<>();

        //get up to maxResult number of sessions that have expired
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(_model.getKind())
                .setFilter(CompositeFilter.and(PropertyFilter.gt(_model.getExpiry(), 0), PropertyFilter.le(_model.getExpiry(), System.currentTimeMillis())))
                .setLimit(_maxResults)
                .build();

        QueryResults<Entity> results;
        if (LOG.isDebugEnabled())
        {
            long start = System.currentTimeMillis();
            results = _datastore.run(query);
            LOG.debug("Expiry query no index in {}ms", System.currentTimeMillis()-start);
        }
        else
            results = _datastore.run(query);
        while (results.hasNext())
        {
            Entity entity = results.next();
            info.add(new ExpiryInfo(entity.getString(_model.getId()),entity.getString(_model.getLastNode()), entity.getLong(_model.getExpiry())));
        }

        return info;
    }
