    protected Set<ExpiryInfo>  queryExpiryByIndex () throws Exception
    {
        long now = System.currentTimeMillis();
        Set<ExpiryInfo> info = new HashSet<>();
        Query<ProjectionEntity> query = Query.newProjectionEntityQueryBuilder()
                .setKind(_model.getKind())
                .setProjection(_model.getId(), _model.getLastNode(), _model.getExpiry())
                .setFilter(CompositeFilter.and(PropertyFilter.gt(_model.getExpiry(), 0), PropertyFilter.le(_model.getExpiry(), now)))
                .setLimit(_maxResults)
                .build();

        QueryResults<ProjectionEntity> presults;
        
        if (LOG.isDebugEnabled())
        {
            long start = System.currentTimeMillis();
            presults = _datastore.run(query);
            LOG.debug("Expiry query by index in {}ms", System.currentTimeMillis()-start);
        }
        else
            presults = _datastore.run(query);

        while (presults.hasNext())
        {
            ProjectionEntity pe = presults.next();
            info.add(new ExpiryInfo(pe.getString(_model.getId()),pe.getString(_model.getLastNode()), pe.getLong(_model.getExpiry())));
        }

        return info;
    }
