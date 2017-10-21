    public final Table getSystemTable(Session session, String name) {

        Table t;
        int   tableIndex;

        if (!isSystemTable(name)) {
            return null;
        }

        tableIndex = getSysTableID(name);
        t          = sysTables[tableIndex];

        // fredt - any system table that is not supported will be null here
        if (t == null) {
            return t;
        }

        // At the time of opening the database, no content is needed.
        // However, table structure is required at this
        // point to allow processing logged View defn's against system
        // tables
        if (!withContent) {
            return t;
        }

        return t;
    }
