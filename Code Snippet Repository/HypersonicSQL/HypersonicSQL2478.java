    private boolean hasFreeSpace(int spaceId, int blockIndex) {

        ba.initialise(false);

        try {
            boolean result = ba.moveToBlock(blockIndex);

            if (result) {
                if (ba.getTableId() == spaceId) {
                    if (ba.getFreeBlockValue() > 0) {
                        return true;
                    }
                }
            }

            return false;
        } finally {
            ba.reset();
        }
    }
