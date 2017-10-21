    void setSortRange(QuerySpecification select) {

        if (this == noSort) {
            return;
        }

        if (primaryTableIndex == null) {
            if (select.isSimpleDistinct) {
                setSortIndex(select);
            }

            if (primaryTableIndex == null) {
                return;
            }
        }

        Index rangeIndex = select.rangeVariables[0].getSortIndex();

        if (rangeIndex == null) {

            // multi-index
            return;
        }

        if (primaryTable != select.rangeVariables[0].rangeTable) {
            return;
        }

        if (rangeIndex == primaryTableIndex) {
            if (allDescending) {
                boolean reversed = select.rangeVariables[0].reverseOrder();

                if (!reversed) {
                    return;
                }
            }

            skipSort       = true;
            skipFullResult = true;
        } else if (!select.rangeVariables[0].joinConditions[0]
                .hasIndexCondition()) {
            if (select.rangeVariables[0].setSortIndex(primaryTableIndex,
                    allDescending)) {
                skipSort       = true;
                skipFullResult = true;
            }
        }
    }
