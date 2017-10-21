    public ResultSet getAttributes(
            String catalog, String schemaPattern, String typeNamePattern,
            String attributeNamePattern) throws SQLException {

        StringBuffer select = toQueryPrefixNoSelect(
            "SELECT TABLE_NAME AS TYPE_CAT, TABLE_NAME AS TYPE_SCHME, TABLE_NAME AS TYPE_NAME, "
            + "TABLE_NAME AS ATTR_NAME, CAST(0 AS INTEGER) AS DATA_TYPE, TABLE_NAME AS ATTR_TYPE_NAME, "
            + "CAST(0 AS INTEGER) AS ATTR_SIZE, CAST(0 AS INTEGER) AS DECIMAL_DIGITS, "
            + "CAST(0 AS INTEGER) AS NUM_PREC_RADIX, CAST(0 AS INTEGER) AS NULLABLE, "
            + "'' AS REMARK, '' AS ATTR_DEF, CAST(0 AS INTEGER) AS SQL_DATA_TYPE, "
            + "CAST(0 AS INTEGER) AS SQL_DATETIME_SUB, CAST(0 AS INTEGER) AS CHAR_OCTECT_LENGTH, "
            + "CAST(0 AS INTEGER) AS ORDINAL_POSITION, '' AS NULLABLE, "
            + "'' AS SCOPE_CATALOG, '' AS SCOPE_SCHEMA, '' AS SCOPE_TABLE, "
            + "CAST(0 AS SMALLINT) AS SCOPE_DATA_TYPE "
            + "FROM INFORMATION_SCHEMA.TABLES ").append(
                and("TABLE_NAME", "=", ""));

        return execute(select.toString());
    }
