        private void endBlockUpdate() {

            if (currentBitMap == null) {
                return;
            }

            if (!currentBitMap.hasChanged()) {
                currentBitMap.keepInMemory(false);

                return;
            }

            int freeUnits      = currentBitMap.bitMap.countSetBits();
            int freeBlockUnits = currentBitMap.bitMap.countSetBitsEnd();

            if (freeUnits == fileBlockItemCount) {
                int currentId =
                    currentDir.getTableIdArray()[currentBlockOffset];

                if (currentId != DataSpaceManager.tableIdSetAside) {
                    setTable(DataSpaceManager.tableIdEmpty);
                    emptySpaceList.addUnique(currentBlockIndex);

                    released++;
                }

                currentBitMap.keepInMemory(false);

                return;
            }

            currentBitMap.keepInMemory(false);

            currentDir.getFreeSpaceArray()[currentBlockOffset] =
                (char) freeUnits;
            currentDir.getFreeBlockArray()[currentBlockOffset] =
                (char) freeBlockUnits;

            currentDir.setChanged(true);
        }
