    public void insertFromScript(Session session, PersistentStore store,
                                 Object[] data) {

        systemUpdateIdentityValue(data);

        if (session.database.getProperties().isVersion18()) {
            for (int i = 0; i < columnCount; i++) {
                if (data[i] != null) {
                    int length;

                    if (colTypes[i].isCharacterType()
                            || colTypes[i].isBinaryType()) {
                        if (data[i] instanceof String) {
                            length = ((String) data[i]).length();
                        } else if (data[i] instanceof BinaryData) {
                            length =
                                (int) ((BinaryData) data[i]).length(session);
                        } else {
                            throw Error.runtimeError(ErrorCode.X_07000,
                                                     "Table");
                        }

                        if (length > colTypes[i].precision) {
                            length = ((length / 10) + 1) * 10;
                            colTypes[i] =
                                Type.getType(colTypes[i].typeCode,
                                             colTypes[i].getCharacterSet(),
                                             colTypes[i].getCollation(),
                                             length, 0);

                            ColumnSchema column = getColumn(i);

                            column.setType(colTypes[i]);
                        }
                    }
                }
            }
        }

        insertData(session, store, data);
    }
