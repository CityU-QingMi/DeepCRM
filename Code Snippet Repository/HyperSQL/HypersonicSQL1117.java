    public final Index createAndAddIndexStructure(Session session,
            HsqlName name, int[] columns, boolean[] descending,
            boolean[] nullsLast, boolean unique, boolean constraint,
            boolean forward) {

        Index newindex = createIndexStructure(name, columns, descending,
                                              nullsLast, unique, constraint,
                                              forward);

        addIndex(session, newindex);

        return newindex;
    }
