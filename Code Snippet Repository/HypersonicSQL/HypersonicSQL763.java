    public Table findUserTable(String name, String schemaName) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(schemaName);

            if (schema == null) {
                return null;
            }

            int i = schema.tableList.getIndex(name);

            if (i == -1) {
                return null;
            }

            return (Table) schema.tableList.get(i);
        } finally {
            readLock.unlock();
        }
    }
