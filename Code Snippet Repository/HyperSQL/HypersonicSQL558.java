    void createResultTable(Session session) {

        HsqlName tableName =
            session.database.nameManager.getSubqueryTableName();
        int tableType = persistenceScope == TableBase.SCOPE_STATEMENT
                        ? TableBase.SYSTEM_SUBQUERY
                        : TableBase.RESULT_TABLE;
        HashMappedList columnList = new HashMappedList();

        for (int i = 0; i < indexLimitVisible; i++) {
            Expression e          = exprColumns[i];
            SimpleName simpleName = e.getSimpleName();
            String     nameString = simpleName.name;
            HsqlName name =
                session.database.nameManager.newColumnSchemaHsqlName(tableName,
                    simpleName);

            if (!accessibleColumns[i]) {
                nameString = HsqlNameManager.getAutoNoNameColumnString(i);
            }

            ColumnSchema column = new ColumnSchema(name, e.dataType, true,
                                                   false, null);

            columnList.add(nameString, column);
        }

        resultTable = new TableDerived(session.database, tableName, tableType,
                                       resultColumnTypes, columnList,
                                       ValuePool.emptyIntArray);
    }
