    private void setTables() {

        if (tables.length == 0) {
            return;
        }

        HashMappedList list = new HashMappedList();

        if (parent != null && parent.scopeTables != null) {
            for (int i = 0; i < parent.scopeTables.size(); i++) {
                list.add(parent.scopeTables.getKey(i),
                         parent.scopeTables.get(i));
            }
        }

        for (int i = 0; i < tables.length; i++) {
            String  name  = tables[i].getName().name;
            boolean added = list.add(name, tables[i]);

            if (!added) {
                throw Error.error(ErrorCode.X_42606, name);
            }
        }

        scopeTables = list;
    }
