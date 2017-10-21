    Table findUserTableForIndex(Session session, String name,
                                String schemaName) {

        readLock.lock();

        try {
            Schema   schema    = (Schema) schemaMap.get(schemaName);
            HsqlName indexName = schema.indexLookup.getName(name);

            if (indexName == null) {
                return null;
            }

            return findUserTable(indexName.parent.name, schemaName);
        } finally {
            readLock.unlock();
        }
    }
