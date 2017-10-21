    Index getIndexForColumns(int[] cols) {

        for (int i = 0; i < indexList.length; i++) {
            if (ArrayUtil.haveEqualSets(indexList[i].getColumns(), cols,
                                        cols.length)) {
                return indexList[i];
            }
        }

        return null;
    }
