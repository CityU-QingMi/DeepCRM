    String getColRemarks(int i) {

        String key;

        if (table.getTableType() != TableBase.INFO_SCHEMA_TABLE) {
            return table.getColumn(i).getName().comment;
        }

        key = getName() + "_" + getColName(i);

        return ResourceBundleHandler.getString(hnd_column_remarks, key);
    }
