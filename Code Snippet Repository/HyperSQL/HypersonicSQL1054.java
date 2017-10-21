    public Index getClusteredIndex() {

        for (int i = 0; i < indexList.length; i++) {
            if (indexList[i].isClustered()) {
                return indexList[i];
            }
        }

        return null;
    }
