    public TableSpaceManagerBlocks(DataSpaceManager spaceManager, int tableId,
                                   int fileBlockSize, int capacity,
                                   int fileScale, int minReuse) {

        this.spaceManager  = spaceManager;
        this.scale         = fileScale;
        this.spaceID       = tableId;
        this.mainBlockSize = fileBlockSize;
        this.minReuse      = minReuse;
        lookup             = new DoubleIntIndex(capacity, true);

        lookup.setValuesSearchTarget();

        this.capacity = capacity;
    }
