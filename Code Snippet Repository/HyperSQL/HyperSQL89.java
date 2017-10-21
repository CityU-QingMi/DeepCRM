    Index getSystemIndex(String indexName) {

        Index[] indexes = indexList;

        for (int i = 0; i < indexes.length; i++) {
            if (indexName.equals(indexes[i].getName().name)) {
                if (indexes[i].isConstraint()) {
                    return indexes[i];
                }
            }
        }

        return null;
    }
