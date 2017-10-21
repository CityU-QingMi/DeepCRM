    protected boolean checkIndexes ()
    {
        long start =0;
        
        try
        {
            Query<ProjectionEntity> query = Query.newProjectionEntityQueryBuilder()
                    .setKind(_model.getKind())
                    .setProjection(_model.getExpiry())
                    .setFilter(PropertyFilter.eq(_model.getId(), "-"))
                    .build();
            _datastore.run(query);
            return true;
        }
        catch (DatastoreException e)
        {
            //need to assume that the problem is the index doesn't exist, because there
            //is no specific code for that
            if (LOG.isDebugEnabled())
                LOG.debug("Check for indexes", e);

            return false;
        }
    }
