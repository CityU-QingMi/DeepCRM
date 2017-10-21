    public Set<String> getSessionIds () throws Exception
    {
        HashSet<String> ids = new HashSet<String>();
        GqlQuery.Builder builder = Query.newGqlQueryBuilder(ResultType.ENTITY, "select * from "+GCloudSessionDataStore.EntityDataModel.KIND);
       
        Query<Entity> query = builder.build();
    
        QueryResults<Entity> results = _ds.run(query);
        assertNotNull(results);
        while (results.hasNext())
        {
            Entity e = results.next();
            ids.add(e.getString("id"));
        }
        
        return ids;
    }
