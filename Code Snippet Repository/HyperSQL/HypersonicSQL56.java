    void checkCheckConstraint(Session session, Table table,
                              ColumnSchema column, Object data) {

        session.sessionData.currentValue = data;

        boolean nomatch = Boolean.FALSE.equals(check.getValue(session));

        session.sessionData.currentValue = null;

        if (nomatch) {
            String[] info = new String[] {
                name.statementName,
                table == null ? ""
                              : table.getName().statementName,
                column == null ? ""
                               : column.getName().statementName,
            };

            throw Error.error(null, ErrorCode.X_23513,
                              ErrorCode.COLUMN_CONSTRAINT, info);
        }
    }
