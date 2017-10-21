    public void compile(Session session, SchemaObject parentObject) {

        ParserDQL p = new ParserDQL(session, new Scanner(session, statement), null);

        p.isViewDefinition = true;

        p.read();

        TableDerived viewSubQueryTable = p.XreadViewSubqueryTable(this, true);

        queryExpression = viewSubQueryTable.queryExpression;

        if (getColumnCount() == 0) {
            if (columnNames == null) {
                columnNames =
                    viewSubQueryTable.queryExpression.getResultColumnNames();
            }

            if (columnNames.length
                    != viewSubQueryTable.queryExpression.getColumnCount()) {
                throw Error.error(ErrorCode.X_42593, getName().statementName);
            }

            TableUtil.setColumnsInSchemaTable(
                this, columnNames, queryExpression.getColumnTypes());
        }

        //
        schemaObjectNames = p.compileContext.getSchemaObjectNames();
        canRecompile      = true;
        baseTable         = queryExpression.getBaseTable();

        if (baseTable == null) {
            return;
        }

        switch (checkOption) {

            case SchemaObject.ViewCheckModes.CHECK_NONE :
            case SchemaObject.ViewCheckModes.CHECK_LOCAL :
            case SchemaObject.ViewCheckModes.CHECK_CASCADE :
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "View");
        }
    }
