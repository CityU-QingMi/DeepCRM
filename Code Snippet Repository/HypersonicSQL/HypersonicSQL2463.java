    private int getBlockIndexLimit() {

        int[] rootArray      = rootBlock.getIntArray();
        int   rootBlockIndex = 0;

        for (; rootBlockIndex < rootArray.length; rootBlockIndex++) {
            if (rootArray[rootBlockIndex] == 0) {
                break;
            }
        }

        if (rootBlockIndex == 0) {
            return 0;
        }

        rootBlockIndex--;

        long position = rootArray[rootBlockIndex];

        position *= (DataSpaceManager.fixedBlockSizeUnit / dataFileScale);

        //
        DirectoryBlockCachedObject currentDir;

        currentDir = (DirectoryBlockCachedObject) directoryStore.get(position,
                false);

        int[] bitmapArray          = currentDir.getBitmapAddressArray();
        int   directoryBlockOffset = 0;

        for (; directoryBlockOffset < bitmapArray.length;
                directoryBlockOffset++) {
            if (bitmapArray[directoryBlockOffset] == 0) {
                break;
            }
        }

        return rootBlockIndex * dirBlockSize + directoryBlockOffset;
    }
