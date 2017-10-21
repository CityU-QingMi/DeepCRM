    public void enforceTypeLimits(Session session, Object[] data) {

        int i = 0;

        try {
            for (; i < columnCount; i++) {
                data[i] = colTypes[i].convertToTypeLimits(session, data[i]);
            }
        } catch (HsqlException e) {
            int code = e.getErrorCode();

            if (code == -ErrorCode.X_22001 || code == -ErrorCode.X_22003
                    || code == -ErrorCode.X_22008) {
                ColumnSchema column = getColumn(i);
                String[]     info   = new String[] {
                    "", tableName.statementName, column.getName().statementName
                };

                throw Error.error(e, code, ErrorCode.COLUMN_CONSTRAINT, info);
            }

            throw e;
        }
    }
