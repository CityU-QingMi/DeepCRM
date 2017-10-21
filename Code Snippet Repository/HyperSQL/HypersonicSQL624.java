    public boolean setDistinctColumnsOnIndex(int[] colMap) {

        if (joinConditions.length != 1) {
            return false;
        }

        int[] indexColMap = joinConditions[0].rangeIndex.getColumns();

        if (colMap.length > indexColMap.length) {
            return false;
        }

        if (colMap.length == indexColMap.length) {
            if (ArrayUtil.haveEqualSets(colMap, indexColMap, colMap.length)) {
                indexDistinctCount = colMap.length;

                return true;
            }
        }

        if (ArrayUtil.haveEqualArrays(colMap, indexColMap, colMap.length)) {
            indexDistinctCount = colMap.length;

            return true;
        }

        return false;
    }
