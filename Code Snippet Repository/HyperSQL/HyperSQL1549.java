    public BlockObjectStore(DataFileCache cache,
                            TableSpaceManager tableSpaceManager,
                            Class objectClass, int storageSize,
                            int blockSize) {

        this.cache        = cache;
        this.spaceManager = tableSpaceManager;
        this.objectClass  = objectClass;
        this.blockSize    = blockSize;
        this.storageSize  = storageSize;

        try {
            this.constructor = objectClass.getConstructor(int.class);
        } catch (Exception e) {
            throw Error.runtimeError(ErrorCode.U_S0500, "BlockObjectStore");
        }
    }
