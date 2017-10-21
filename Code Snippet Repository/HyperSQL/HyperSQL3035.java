        private TableDerived getNamedSubQuery(String name) {

            if (baseContext != null) {
                TableDerived td = baseContext.getNamedSubQuery(name);

                if (td != null) {
                    return td;
                }
            }

            if (namedSubqueries == null) {
                return null;
            }

            for (int i = subqueryDepth; i >= 0; i--) {
                if (namedSubqueries.size() <= i) {
                    continue;
                }

                HashMappedList set = (HashMappedList) namedSubqueries.get(i);

                if (set == null) {
                    continue;
                }

                TableDerived td = (TableDerived) set.get(name);

                if (td != null) {
                    return td;
                }
            }

            return null;
        }
