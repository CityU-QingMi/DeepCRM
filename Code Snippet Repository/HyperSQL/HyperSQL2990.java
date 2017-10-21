        private void clearSubqueries() {

            if (namedSubqueries != null) {
                if (namedSubqueries.size() > subqueryDepth) {
                    HashMappedList set =
                        (HashMappedList) namedSubqueries.get(subqueryDepth);

                    if (set != null) {
                        set.clear();
                    }
                }
            }
        }
