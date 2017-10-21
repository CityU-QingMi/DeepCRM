    public void addSessionTable(Table table) {

        if (sessionTables == null) {
            sessionTables = new HashMappedList();
        }

        if (sessionTables.containsKey(table.getName().name)) {
            throw Error.error(ErrorCode.X_42504);
        }

        sessionTables.add(table.getName().name, table);
    }
