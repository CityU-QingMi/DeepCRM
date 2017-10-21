    private void setDirectoryBlocksAsTable(int tableId, int blockIndex,
                                           int blockCount) {

        int                        directoryIndex = -1;
        DirectoryBlockCachedObject directory      = null;

        for (int i = blockIndex; i < blockIndex + blockCount; i++) {
            if (directoryIndex != i / dirBlockSize) {
                if (directory != null) {
                    directory.keepInMemory(false);
                }

                directory      = getDirectory(i, true);
                directoryIndex = i / dirBlockSize;
            }

            int offset = i % dirBlockSize;

            directory.getTableIdArray()[offset] = tableId;

            directory.setChanged(true);
        }

        directory.keepInMemory(false);
    }
