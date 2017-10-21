    void removeExportedKeys(Table toDrop) {

        writeLock.lock();

        try {

            // toDrop.schema may be null because it is not registered
            Schema schema =
                (Schema) schemaMap.get(toDrop.getSchemaName().name);

            for (int i = 0; i < schema.tableList.size(); i++) {
                Table        table       = (Table) schema.tableList.get(i);
                Constraint[] constraints = table.getConstraints();

                for (int j = constraints.length - 1; j >= 0; j--) {
                    Table refTable = constraints[j].getRef();

                    if (toDrop == refTable) {
                        table.removeConstraint(j);
                    }
                }
            }
        } finally {
            writeLock.unlock();
        }
    }
