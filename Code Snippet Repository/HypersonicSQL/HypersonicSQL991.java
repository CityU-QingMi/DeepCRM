    void setRowActionProperties(Result result, int action,
                                StatementQuery statement, Type[] types) {

        QueryExpression qe = statement.queryExpression;

        this.result             = result;
        this.actionType         = action;
        this.baseTable          = qe.getBaseTable();
        this.types              = types;
        this.baseColumnMap      = qe.getBaseTableColumnMap();
        this.writeTableNames[0] = baseTable.getName();

        // used for statement logging - needs improvements to list only the updated values
        this.sql                = statement.getSQL();
        this.parameterMetaData  = qe.getMetaData();
    }
