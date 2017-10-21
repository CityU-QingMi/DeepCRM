    private void copyBlockSet(int[][] source, int[][] target) {

        int sourceIndex  = 0;
        int targetIndex  = 0;
        int sourceOffset = 0;
        int targetOffset = 0;

        while (true) {
            byte[] bytes = getLobStore().getBlockBytes(
                source[sourceIndex][LOBS.BLOCK_ADDR] + sourceOffset, 1);

            getLobStore().setBlockBytes(bytes,
                                        target[targetIndex][LOBS.BLOCK_ADDR]
                                        + targetOffset, 1);

            sourceOffset++;
            targetOffset++;

            if (sourceOffset == source[sourceIndex][LOBS.BLOCK_COUNT]) {
                sourceOffset = 0;

                sourceIndex++;
            }

            if (targetOffset == target[targetIndex][LOBS.BLOCK_COUNT]) {
                targetOffset = 0;

                targetIndex++;
            }

            if (sourceIndex == source.length) {
                break;
            }

            if (targetIndex == target.length) {
                break;
            }
        }

        storeModified = true;
    }
