        protected void initialiseIterator() {

            if (condIndex == 0) {
                hasLeftOuterRow = rangeVar.isLeftJoin;
            }

            if (conditions[condIndex].isFalse) {
                it = conditions[condIndex].rangeIndex.emptyIterator();

                return;
            }

            rangeVar.rangeTable.materialiseCorrelated(session);

            if (conditions[condIndex].indexCond == null) {
                if (conditions[condIndex].reversed) {
                    it = conditions[condIndex].rangeIndex.lastRow(session,
                            store, rangeVar.indexDistinctCount, null);
                } else {
                    it = conditions[condIndex].rangeIndex.firstRow(session,
                            store, rangeVar.indexDistinctCount, null);
                }
            } else {
                getFirstRow();

                if (!conditions[condIndex].isJoin) {
                    hasLeftOuterRow = false;
                }
            }
        }
