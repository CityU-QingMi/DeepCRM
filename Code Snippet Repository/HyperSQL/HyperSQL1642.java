    public DirectoryBlockCachedObject[] getDirectoryList() {

        int                          count = 0;
        DirectoryBlockCachedObject[] directoryList;
        int[]                        rootArray = rootBlock.getIntArray();

        while (rootArray[count] != 0) {
            count++;
        }

        directoryList = new DirectoryBlockCachedObject[count];

        for (int i = 0; i < directoryList.length; i++) {
            directoryList[i] = getDirectory(i * dirBlockSize, false);
        }

        return directoryList;
    }
