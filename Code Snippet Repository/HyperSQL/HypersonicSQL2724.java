    public final RowIterator rowIterator() {

        Index index = indexList[0];

        for (int i = 0; i < indexList.length; i++) {
            if (indexList[i].isClustered()) {
                index = indexList[i];

                break;
            }
        }

        return index.firstRow(this);
    }
