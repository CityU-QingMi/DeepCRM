    Index createIndexForColumns(Session session, int[] columns) {

        Index index = null;
        HsqlName indexName = database.nameManager.newAutoName("IDX_T",
            getSchemaName(), getName(), SchemaObject.INDEX);

        try {
            index = createAndAddIndexStructure(session, indexName, columns,
                                               null, null, false, false,
                                               false);
        } catch (Throwable t) {
            return null;
        }

        return index;
    }
