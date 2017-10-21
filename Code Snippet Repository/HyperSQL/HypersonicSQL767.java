    public int getTableIndex(Table table) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(table.getSchemaName().name);

            if (schema == null) {
                return -1;
            }

            HsqlName name = table.getName();

            return schema.tableList.getIndex(name.name);
        } finally {
            readLock.unlock();
        }
    }
