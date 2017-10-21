        boolean moveToBlock(int fileBlockIndex) {

            if (currentBlockIndex != fileBlockIndex) {
                endBlockUpdate();

                currentBitMap = null;

                if (currentDirIndex != fileBlockIndex / dirBlockSize) {
                    reset();

                    currentDirIndex = fileBlockIndex / dirBlockSize;
                    currentDir = getDirectory(fileBlockIndex, currentKeep);
                }

                if (currentDir == null) {
                    reset();

                    return false;
                }

                currentBlockIndex  = fileBlockIndex;
                currentBlockOffset = fileBlockIndex % dirBlockSize;

                long position =
                    currentDir.getBitmapAddressArray()[currentBlockOffset];

                if (position == 0) {
                    reset();

                    return false;
                }

                if (currentKeep) {
                    position *= (DataSpaceManager.fixedBlockSizeUnit
                                 / dataFileScale);
                    currentBitMap =
                        (BitMapCachedObject) bitMapStore.get(position, true);
                }
            }

            return true;
        }
