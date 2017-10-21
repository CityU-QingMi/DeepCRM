    public final Index createIndexStructure(HsqlName name, int[] columns,
                                     boolean[] descending,
                                     boolean[] nullsLast, boolean unique,
                                     boolean constraint, boolean forward) {

        int    s     = columns.length;
        int[]  cols  = new int[s];
        Type[] types = new Type[s];

        for (int j = 0; j < s; j++) {
            cols[j]  = columns[j];
            types[j] = colTypes[cols[j]];
        }

        long id = database.persistentStoreCollection.getNextId();
        Index newIndex = database.logger.newIndex(name, id, this, cols,
            descending, nullsLast, types, false, unique, constraint, forward);

        return newIndex;
    }
