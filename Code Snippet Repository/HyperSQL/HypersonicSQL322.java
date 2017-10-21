    private Statement compileLockCatalog() {

        boolean isLock    = processTrueOrFalse();
        int statementType = isLock ? StatementTypes.TRANSACTION_LOCK_CATALOG
                                   : StatementTypes.TRANSACTION_UNLOCK_CATALOG;
        HsqlName[] writeTableNames =
            isLock ? database.schemaManager.getCatalogAndBaseTableNames()
                   : null;
        Statement cs = new StatementSession(statementType, null,
                                            writeTableNames);

        return cs;
    }
