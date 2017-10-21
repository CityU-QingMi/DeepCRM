    public RowIterator rowIteratorClustered(PersistentStore store) {

        Index index = getClusteredIndex();

        if (index == null) {
            index = getPrimaryIndex();
        }

        return index.firstRow(store);
    }
