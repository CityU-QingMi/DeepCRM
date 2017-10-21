        private void unregisterSubqueries() {

            if (namedSubqueries == null) {
                return;
            }

            for (int i = subqueryDepth; i < namedSubqueries.size(); i++) {
                namedSubqueries.set(i, null);
            }
        }
