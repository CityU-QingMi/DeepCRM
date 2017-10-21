    public void setTable(int index, Table table) {

        writeLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(table.getSchemaName().name);

            schema.tableList.set(index, table.getName().name, table);
        } finally {
            writeLock.unlock();
        }
    }
