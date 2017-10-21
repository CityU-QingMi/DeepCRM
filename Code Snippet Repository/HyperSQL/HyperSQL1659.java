    private DirectoryBlockCachedObject getDirectory(int fileBlockIndex,
            boolean keep) {

        DirectoryBlockCachedObject directory;
        int                        indexInRoot = fileBlockIndex / dirBlockSize;
        long position = rootBlock.getIntArray()[indexInRoot];

        if (position == 0) {
            return null;
        }

        position *= (DataSpaceManager.fixedBlockSizeUnit / dataFileScale);
        directory = (DirectoryBlockCachedObject) directoryStore.get(position,
                keep);

        return directory;
    }
