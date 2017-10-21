    public boolean isCatalogLock(int model) {

        switch (group) {

            case StatementTypes.X_SQL_SCHEMA_MANIPULATION :

                // in MVCC log replay statement is not followed by COMMIT so no lock
                if (type == StatementTypes.ALTER_SEQUENCE) {
                    return false;
                }

                if (writeTableNames.length == 0) {
                    return false;
                }
            case StatementTypes.X_SQL_SCHEMA_DEFINITION :
                return model == TransactionManager.MVCC;

            case StatementTypes.X_HSQLDB_SCHEMA_MANIPULATION :
            case StatementTypes.X_HSQLDB_DATABASE_OPERATION :
                return true;

            case StatementTypes.X_HSQLDB_NONBLOCK_OPERATION :
            default :
                return false;
        }
    }
