    void createResultTable(Session session) {

        HsqlName       tableName;
        HashMappedList columnList;
        int            tableType;

        tableName = session.database.nameManager.getSubqueryTableName();
        tableType = persistenceScope == TableBase.SCOPE_STATEMENT
                    ? TableBase.SYSTEM_SUBQUERY
                    : TableBase.RESULT_TABLE;
        columnList = leftQueryExpression.getUnionColumns();
        resultTable = new TableDerived(session.database, tableName, tableType,
                                       unionColumnTypes, columnList,
                                       ValuePool.emptyIntArray);
    }
