    public int findColumn(String schemaName, String tableName,
                          String columnName) {

        if (namedJoinColumnExpressions != null
                && namedJoinColumnExpressions.containsKey(columnName)) {
            if (tableName != null && !resolvesTableName(tableName)) {
                return -1;
            }
        }

        if (resolvesSchemaAndTableName(schemaName, tableName)) {
            return findColumn(columnName);
        }

        return -1;
    }
