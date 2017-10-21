        private void initSubqueryNames() {

            if (namedSubqueries == null) {
                namedSubqueries = new HsqlArrayList();
            }

            if (namedSubqueries.size() <= subqueryDepth) {
                namedSubqueries.setSize(subqueryDepth + 1);
            }

            HashMappedList set =
                (HashMappedList) namedSubqueries.get(subqueryDepth);

            if (set == null) {
                set = new HashMappedList();

                namedSubqueries.set(subqueryDepth, set);
            }
        }
