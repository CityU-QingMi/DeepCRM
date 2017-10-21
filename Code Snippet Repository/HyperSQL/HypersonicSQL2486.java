        void setTable(int tableId) {

            currentDir.getTableIdArray()[currentBlockOffset]   = tableId;
            currentDir.getFreeSpaceArray()[currentBlockOffset] = (char) 0;
            currentDir.getFreeBlockArray()[currentBlockOffset] = (char) 0;

            currentDir.setChanged(true);
            currentBitMap.bitMap.reset();
            currentBitMap.setChanged(true);
        }
