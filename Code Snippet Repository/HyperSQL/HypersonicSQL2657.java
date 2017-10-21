    synchronized public void removeStore(TableBase table) {

        switch (table.persistenceScope) {

            case TableBase.SCOPE_ROUTINE :
                rowStoreMapRoutine.remove(table.getPersistenceId());
                break;

            case TableBase.SCOPE_STATEMENT :
                rowStoreMapStatement.remove(table.getPersistenceId());
                break;

            // TEMP TABLE default, SYSTEM_TABLE + INFO_SCHEMA_TABLE
            case TableBase.SCOPE_FULL :
            case TableBase.SCOPE_TRANSACTION :
                rowStoreMapTransaction.remove(table.getPersistenceId());
                break;

            case TableBase.SCOPE_SESSION :
                rowStoreMapSession.remove(table.getPersistenceId());
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "PersistentStoreCollectionSession");
        }
    }
