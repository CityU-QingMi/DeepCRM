    public void add(Session session, CachedObject object, boolean tx) {

        if (isCached) {
            int size = object.getRealSize(cache.rowOut);

            size += indexList.length * NodeAVLDisk.SIZE_IN_BYTE;
            size = cache.rowOut.getStorageSize(size);

            object.setStorageSize(size);

            long pos = tableSpace.getFilePosition(size, false);

            object.setPos(pos);
            cache.add(object, false);
        }

        Object[] data = ((Row) object).getData();

        for (int i = 0; i < nullsList.length; i++) {
            if (data[i] == null) {
                nullsList[i] = true;
            }
        }
    }
