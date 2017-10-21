    public boolean isCatalogChange() {

        switch (group) {

            case StatementTypes.X_SQL_SCHEMA_DEFINITION :
            case StatementTypes.X_SQL_SCHEMA_MANIPULATION :
            case StatementTypes.X_HSQLDB_SCHEMA_MANIPULATION :
                return true;

            default :
                return false;
        }
    }
