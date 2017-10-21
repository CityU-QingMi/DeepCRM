    TableDerived[] getSubqueries(Session session) {

        OrderedHashSet subQueries = null;

        if (expression != null) {
            subQueries = expression.collectAllSubqueries(subQueries);
        }

        for (int i = 0; i < arguments.length; i++) {
            subQueries = arguments[i].collectAllSubqueries(subQueries);
        }

        if (subQueries == null || subQueries.size() == 0) {
            return TableDerived.emptyArray;
        }

        TableDerived[] subQueryArray = new TableDerived[subQueries.size()];

        subQueries.toArray(subQueryArray);

        for (int i = 0; i < subqueries.length; i++) {
            subQueryArray[i].prepareTable(session);
        }

        return subQueryArray;
    }
