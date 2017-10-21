    synchronized public PersistentStore findStore(TableBase table) {

        PersistentStore store = null;

        switch (table.persistenceScope) {

            case TableBase.SCOPE_ROUTINE :
                store = (PersistentStore) rowStoreMapRoutine.get(
                    table.getPersistenceId());
                break;

            case TableBase.SCOPE_STATEMENT :
                store = (PersistentStore) rowStoreMapStatement.get(
                    table.getPersistenceId());
                break;

            // TEMP TABLE default, SYSTEM_TABLE + INFO_SCHEMA_TABLE
            case TableBase.SCOPE_FULL :
            case TableBase.SCOPE_TRANSACTION :
                store = (PersistentStore) rowStoreMapTransaction.get(
                    table.getPersistenceId());
                break;

            case TableBase.SCOPE_SESSION :
                store = (PersistentStore) rowStoreMapSession.get(
                    table.getPersistenceId());
                break;
        }

        return store;
    }
