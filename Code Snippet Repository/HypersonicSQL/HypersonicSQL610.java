        private boolean lookupAndTest() {

            long    position = it.getRowId();
            boolean result   = !lookup.contains(position);

            if (result) {
                if (conditions[condIndex].nonIndexCondition != null
                        && !conditions[condIndex].nonIndexCondition
                            .testCondition(session)) {
                    result = false;
                }
            }

            return result;
        }
