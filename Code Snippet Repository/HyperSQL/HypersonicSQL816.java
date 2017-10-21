    public void createSystemTables() {

        dualTable = TableUtil.newSingleColumnTable(database,
                SqlInvariants.DUAL_TABLE_HSQLNAME, TableBase.SYSTEM_TABLE,
                SqlInvariants.DUAL_COLUMN_HSQLNAME, Type.SQL_VARCHAR);

        dualTable.insertSys(database.sessionManager.getSysSession(),
                            dualTable.getRowStore(null), new Object[]{ "X" });
        dualTable.setDataReadOnly(true);

        Type[] columnTypes = new Type[] {
            Type.SQL_BIGINT, Type.SQL_BIGINT, Type.SQL_BIGINT,
            TypeInvariants.SQL_IDENTIFIER, TypeInvariants.SQL_IDENTIFIER,
            Type.SQL_BOOLEAN
        };
        HsqlName       tableName = database.nameManager.getSubqueryTableName();
        HashMappedList columnList = new HashMappedList();

        for (int i = 0; i < columnTypes.length; i++) {
            HsqlName name = HsqlNameManager.getAutoColumnName(i + 1);
            ColumnSchema column = new ColumnSchema(name, columnTypes[i], true,
                                                   false, null);

            columnList.add(name.name, column);
        }

        dataChangeTable = new TableDerived(database, tableName,
                                           TableBase.CHANGE_SET_TABLE,
                                           columnTypes, columnList,
                                           new int[]{ 0 });

        dataChangeTable.createIndexForColumns(null, new int[]{ 1 });
    }
