    public void add(CachedObject object, boolean keep) {

        int size = object.getRealSize(cache.rowOut);

        if (size > storageSize) {
            throw Error.runtimeError(ErrorCode.U_S0500, "BlockObjectStore");
        }

        object.setStorageSize(storageSize);

        long pos = spaceManager.getFilePosition(storageSize, true);

        object.setPos(pos);
        cache.add(object, keep);
    }
