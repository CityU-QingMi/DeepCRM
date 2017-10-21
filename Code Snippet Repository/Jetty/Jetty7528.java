    public void assertSessions(int count) throws Exception
    {        
        Query<Key> query = Query.newKeyQueryBuilder().setKind(GCloudSessionDataStore.EntityDataModel.KIND).build();
        QueryResults<Key> results = _ds.run(query);
        assertNotNull(results);
        int actual = 0;
        while (results.hasNext())
        { 
            results.next();
            ++actual;
        }       
        assertEquals(count, actual);
    }
