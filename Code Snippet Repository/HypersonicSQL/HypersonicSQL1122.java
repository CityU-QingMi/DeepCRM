    public final Index createIndex(Session session, HsqlName name,
                                   int[] columns, boolean[] descending,
                                   boolean[] nullsLast, boolean unique,
                                   boolean constraint, boolean forward) {

        Index newIndex = createAndAddIndexStructure(session, name, columns,
            descending, nullsLast, unique, constraint, forward);

        return newIndex;
    }
