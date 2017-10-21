    public void add(Session session, CachedObject object, boolean tx) {

        int size = object.getRealSize(rowOut);

        size += indexList.length * NodeAVLDisk.SIZE_IN_BYTE;
        size = rowOut.getStorageSize(size);

        object.setStorageSize(size);

        long pos = tableSpace.getFilePosition(size, false);

        object.setPos(pos);

        if (tx) {
            RowAction.addInsertAction(session, table, (Row) object);
            database.txManager.addTransactionInfo(object);
        }

        cache.add(object, false);

        storageSize += size;
    }
