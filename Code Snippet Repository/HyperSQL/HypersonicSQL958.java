    void materializeSubQueries(Session session) {

        HashSet subqueryPopFlags = new HashSet();

        for (int i = 0; i < subqueries.length; i++) {
            TableDerived td = subqueries[i];

            if (!subqueryPopFlags.add(td)) {
                continue;
            }

            if (!td.isCorrelated()) {
                td.materialise(session);
            }
        }
    }
