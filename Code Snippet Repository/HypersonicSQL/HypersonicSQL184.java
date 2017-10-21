    private Boolean testAllAnyCondition(Session session) {

        Object[]     rowData = nodes[LEFT].getRowValue(session);
        TableDerived td      = nodes[RIGHT].table;

        td.materialiseCorrelated(session);

        Boolean result = getAllAnyValue(session, rowData, td);

        return result;
    }
