        private boolean addToIndexConditions(Expression e) {

            if (opType == OpTypes.EQUAL || opType == OpTypes.IS_NULL) {
                if (indexedColumnCount < rangeIndex.getColumnCount()) {
                    if (rangeIndex.getColumns()[indexedColumnCount]
                            == e.getLeftNode().getColumnIndex()) {
                        indexCond[indexedColumnCount]  = e;
                        opType                         = e.opType;
                        opTypes[indexedColumnCount]    = e.opType;
                        opTypeEnd                      = OpTypes.MAX;
                        opTypesEnd[indexedColumnCount] = OpTypes.MAX;

                        indexedColumnCount++;

                        return true;
                    }
                }
            }

            return false;
        }
