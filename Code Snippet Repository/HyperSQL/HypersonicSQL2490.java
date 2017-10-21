    private void createFileBlockInDirectory(int fileBlockIndex, int tableId) {

        // add keep
        BitMapCachedObject bitMap = new BitMapCachedObject(bitmapIntSize);

        bitMapStore.add(bitMap, false);

        //
        int bitmapBlockPos = (int) (bitMap.getPos() * dataFileScale
                                    / DataSpaceManager.fixedBlockSizeUnit);
        int blockOffset = fileBlockIndex % dirBlockSize;
        DirectoryBlockCachedObject directory = getDirectory(fileBlockIndex,
            true);

        if (directory == null) {
            createDirectory(fileBlockIndex);

            directory = getDirectory(fileBlockIndex, true);
        }

        directory.getTableIdArray()[blockOffset]       = tableId;
        directory.getBitmapAddressArray()[blockOffset] = bitmapBlockPos;

        directory.setChanged(true);
        directory.keepInMemory(false);
    }
