    public void setBlockBytes(byte[] dataBytes, long position, int offset,
                              int length) {

        while (length > 0) {
            int largeBlockIndex = (int) (position / largeBlockSize);

            if (largeBlockIndex >= byteStoreList.size()) {
                byteStoreList.add(new byte[largeBlockSize]);
            }

            byte[] largeBlock = (byte[]) byteStoreList.get(largeBlockIndex);
            int    offsetInLargeBlock = (int) (position % largeBlockSize);
            int    currentLength      = length;

            if ((offsetInLargeBlock + currentLength) > largeBlockSize) {
                currentLength = largeBlockSize - offsetInLargeBlock;
            }

            System.arraycopy(dataBytes, offset, largeBlock,
                             offsetInLargeBlock, currentLength);

            position += currentLength;
            offset   += currentLength;
            length   -= currentLength;
        }
    }
