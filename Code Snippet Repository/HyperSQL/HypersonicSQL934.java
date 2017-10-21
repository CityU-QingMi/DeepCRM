    void setLoopStatement(HsqlName name, StatementQuery cursorStatement) {

        loopCursor = cursorStatement;

        HsqlName[] colNames =
            cursorStatement.queryExpression.getResultColumnNames();
        Type[] colTypes = cursorStatement.queryExpression.getColumnTypes();
        ColumnSchema[] columns = new ColumnSchema[colNames.length];

        for (int i = 0; i < colNames.length; i++) {
            columns[i] = new ColumnSchema(colNames[i], colTypes[i], false,
                                          false, null);

            columns[i].setParameterMode(SchemaObject.ParameterModes.PARAM_IN);
        }

        setLocalDeclarations(columns);
    }
