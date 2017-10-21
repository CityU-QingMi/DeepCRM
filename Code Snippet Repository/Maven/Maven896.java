    public void flush()
    {
        for ( CacheRecord record : cache.values() )
        {
            ClassRealm realm = record.realm;
            try
            {
                realm.getWorld().disposeRealm( realm.getId() );
            }
            catch ( NoSuchRealmException e )
            {
                // ignore
            }
        }
        cache.clear();
    }
