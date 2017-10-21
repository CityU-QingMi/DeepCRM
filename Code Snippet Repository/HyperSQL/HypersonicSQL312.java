    public static Statement getAutoCheckpointStatement(Database database) {

        HsqlName[] names =
            database.schemaManager.getCatalogAndBaseTableNames();
        Object[] args = new Object[]{ Boolean.FALSE };
        Statement cs = new StatementCommand(StatementTypes.DATABASE_CHECKPOINT,
                                            args, null, names);

        cs.setCompileTimestamp(database.txManager.getGlobalChangeTimestamp());
        cs.setSQL(Tokens.T_CHECKPOINT);

        return cs;
    }
