    public static int getTableOidForColumn(int colIndex, ResultMetaData md) {
        if (!md.isTableColumn(colIndex)) {
            return 0;
        }
        ColumnBase col = md.columns[colIndex];
        int hashCode = (col.getSchemaNameString() + '.'
            + col.getTableNameString()).hashCode();
        if (hashCode < 0) {
            hashCode *= -1;
        }
        return hashCode;
    }
