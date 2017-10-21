    public void prepareTable(Session session, HsqlName[] columns) {

        prepareTable(session);

        if (columns != null) {
            if (columns.length != columnList.size()) {
                throw Error.error(ErrorCode.X_42593);
            }

            for (int i = 0; i < columnCount; i++) {
                columnList.setKey(i, columns[i].name);

                ColumnSchema col = (ColumnSchema) columnList.get(i);

                col.setName(columns[i]);
            }
        }
    }
