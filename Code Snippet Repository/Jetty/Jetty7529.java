    public void deleteSessions () throws Exception
    {
        Query<Key> query = Query.newKeyQueryBuilder().setKind(GCloudSessionDataStore.EntityDataModel.KIND).build();
        QueryResults<Key> results = _ds.run(query);

        if (results != null)
        {
            List<Key> keys = new ArrayList<Key>();

            while (results.hasNext())
            { 
                keys.add(results.next());
            }

            _ds.delete(keys.toArray(new Key[keys.size()]));
        }

        assertSessions(0);
    }
