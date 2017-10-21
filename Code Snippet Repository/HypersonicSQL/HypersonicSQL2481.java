        boolean nextBlockForTable(int tableId) {

            for (;;) {
                boolean result = moveToBlock(currentBlockIndex + 1);

                if (!result) {
                    return false;
                }

                if (getTableId() == tableId) {
                    return true;
                }
            }
        }
