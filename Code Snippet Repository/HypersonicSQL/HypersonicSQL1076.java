    int getIndexIndex(String indexName) {

        Index[] indexes = indexList;

        for (int i = 0; i < indexes.length; i++) {
            if (indexName.equals(indexes[i].getName().name)) {
                return i;
            }
        }

        // no such index
        return -1;
    }
