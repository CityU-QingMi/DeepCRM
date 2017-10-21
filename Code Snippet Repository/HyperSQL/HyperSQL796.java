    JDBCColumnMetaData getColumnMetaData(int i) {

        JDBCColumnMetaData meta = new JDBCColumnMetaData();

        try {
            meta.catalogName          = getCatalogName(i);
            meta.columnClassName      = getColumnClassName(i);
            meta.columnDisplaySize    = getColumnDisplaySize(i);
            meta.columnLabel          = getColumnLabel(i);
            meta.columnName           = getColumnName(i);
            meta.columnType           = getColumnType(i);
            meta.isAutoIncrement      = isAutoIncrement(i);
            meta.isCaseSensitive      = isCaseSensitive(i);
            meta.isCurrency           = isCurrency(i);
            meta.isDefinitelyWritable = isDefinitelyWritable(i);
            meta.isNullable           = isNullable(i);
            meta.isReadOnly           = isReadOnly(i);
            meta.isSearchable         = isSearchable(i);
            meta.isSigned             = isSigned(i);
            meta.isWritable           = isWritable(i);
            meta.precision            = getPrecision(i);
            meta.scale                = getScale(i);
            meta.schemaName           = getSchemaName(i);
            meta.tableName            = getTableName(i);
        } catch (SQLException e) {
        }

        return meta;
    }
