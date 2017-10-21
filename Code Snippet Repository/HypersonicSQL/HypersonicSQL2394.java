    public CachedObject get(RowInputInterface in) {

        CachedObject object = getNewInstance();

        object.read(in);

        int size = object.getRealSize(cache.rowOut);

        if (size > storageSize) {
            throw Error.runtimeError(ErrorCode.U_S0500, "BlockObjectStore");
        }

        object.setStorageSize(storageSize);

        return object;
    }
