        private void registerSubquery(String name) {

            initSubqueryNames();

            HashMappedList set =
                (HashMappedList) namedSubqueries.get(subqueryDepth);
            boolean added = set.add(name, null);

            if (!added) {
                throw Error.error(ErrorCode.X_42504);
            }

            parameterIndexes.put(name, parameters.size());
        }
