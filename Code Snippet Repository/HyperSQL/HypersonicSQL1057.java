    public void createPrimaryKey(HsqlName indexName, int[] columns,
                                 boolean columnsNotNull) {

        if (columns == null) {
            columns = ValuePool.emptyIntArray;
        }

        for (int i = 0; i < columns.length; i++) {
            getColumn(columns[i]).setPrimaryKey(true);
        }

        setColumnStructures();

        Type[] primaryKeyTypes = new Type[columns.length];

        ArrayUtil.projectRow(colTypes, columns, primaryKeyTypes);

        HsqlName name = indexName;

        if (name == null) {
            name = database.nameManager.newAutoName("IDX", getSchemaName(),
                    getName(), SchemaObject.INDEX);
        }

        createPrimaryIndex(columns, primaryKeyTypes, name);
        setBestRowIdentifiers();
    }
