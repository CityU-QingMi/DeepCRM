    private long getStatementID(HsqlName schema, String sql) {

        LongValueHashMap sqlMap =
            (LongValueHashMap) schemaMap.get(schema.hashCode());

        if (sqlMap == null) {
            return -1;
        }

        return sqlMap.get(sql, -1);
    }
