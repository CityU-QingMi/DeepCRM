    private void createDirectory(int fileBlockIndex) {

        DirectoryBlockCachedObject directory;

        directory = new DirectoryBlockCachedObject(dirBlockSize);

        directoryStore.add(directory, false);

        int indexInRoot = fileBlockIndex / dirBlockSize;
        int blockPosition = (int) (directory.getPos() * dataFileScale
                                   / DataSpaceManager.fixedBlockSizeUnit);

        rootBlock.getIntArray()[indexInRoot] = blockPosition;

        rootBlock.setChanged(true);
    }
