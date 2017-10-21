    private NodeAVLDiskLarge findNode(PersistentStore store) {

        if (row.isInMemory()) {
            return this;
        }

        RowAVLDisk r = (RowAVLDisk) store.get(row.getPos(), false);

        if (r == null) {
            String tableName = "";

            if (row.getTable().getTableType() == Table.CACHED_TABLE) {
                tableName = ((Table) row.getTable()).getName().name;
            }

            store.getCache().logSevereEvent(tableName + " NodeAVLDiskLarge "
                                            + row.getPos(), null);

            return this;
        }

        return (NodeAVLDiskLarge) r.getNode(iId);
    }
