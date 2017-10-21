    public JDBCResultSet(JDBCConnection conn, JDBCStatementBase s, Result r,
                         ResultMetaData metaData) {
        this(conn, r, metaData);
        this.statement  = s;

        isScrollable    = ResultProperties.isScrollable(rsProperties);

        if (ResultProperties.isUpdatable(rsProperties)) {
            isUpdatable  = true;
            isInsertable = true;

            for (int i = 0; i < metaData.colIndexes.length; i++) {
                if (metaData.colIndexes[i] < 0) {
                    isInsertable = false;

                    break;
                }
            }

            preparedStatement = new JDBCPreparedStatement(s.connection, result);
        }
    }
