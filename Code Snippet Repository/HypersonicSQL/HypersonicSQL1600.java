    private void setCurrentProperties() throws SQLException {
        ResultSet rs = executeSelect("SYSTEM_PROPERTIES",
           "PROPERTY_NAME IN "+
           "('sql.concat_nulls', 'sql.nulls_first' , 'sql.nulls_order')");

        while(rs.next()) {
            String prop = rs.getString(2);
            boolean value = Boolean.valueOf(rs.getString(3));

            if (prop.equals("sql.concat_nulls")) {
                concatNulls = value;
            } else

            if (prop.equals("sql.nulls_first")) {
                nullsFirst = value;
            } else

            if (prop.equals("sql.nulls_order")) {
                nullsOrder = value;
            }
        }

        rs.close();
    }
