    StatementSchema compileCreateProcedureOrFunction(boolean orReplace) {

        Routine  routine = readCreateProcedureOrFunction();
        Object[] args    = new Object[]{ routine };
        String   sql     = getLastPart();
        StatementSchema cs = new StatementSchema(sql,
            StatementTypes.CREATE_ROUTINE, args, null,
            database.schemaManager.getCatalogNameArray());

        return cs;
    }
