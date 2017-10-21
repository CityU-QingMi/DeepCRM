    public HsqlArrayList getAllTables(boolean withLobTables) {

        readLock.lock();

        try {
            HsqlArrayList alltables = new HsqlArrayList();
            String[]      schemas   = getSchemaNamesArray();

            for (int i = 0; i < schemas.length; i++) {
                String name = schemas[i];

                if (!withLobTables && SqlInvariants.isLobsSchemaName(name)) {
                    continue;
                }

                if (SqlInvariants.isSystemSchemaName(name)) {
                    continue;
                }

                HashMappedList current = getTables(name);

                alltables.addAll(current.values());
            }

            return alltables;
        } finally {
            readLock.unlock();
        }
    }
