    public TableDerived(Database database, HsqlName name, int type,
                        Type[] columnTypes, HashMappedList columnList,
                        int[] pkColumns) {

        this(database, name, type);

        this.colTypes   = columnTypes;
        this.columnList = columnList;
        columnCount     = columnList.size();

        createPrimaryKey(null, pkColumns, true);
    }
