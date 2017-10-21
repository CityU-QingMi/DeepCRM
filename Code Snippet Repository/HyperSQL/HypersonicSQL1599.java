    private ResultSet executeSelect(String table,
                                    String where) throws SQLException {

        String select = selstar + table;

        if (where != null) {
            select += " WHERE " + where;
        }

        return execute(select);
    }
