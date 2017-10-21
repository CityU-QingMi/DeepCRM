    public static void copyMoveSegment(Object source, Object dest, int size,
                                       int index, int segmentSize,
                                       int destIndex) {

        boolean forward   = index < destIndex;
        int     sliceSize = forward ? index
                                    : destIndex;

        System.arraycopy(source, 0, dest, 0, sliceSize);

        sliceSize = forward ? size - destIndex - segmentSize
                            : size - index - segmentSize;

        int sliceIndex = forward ? destIndex + segmentSize
                                 : index + segmentSize;

        System.arraycopy(source, sliceIndex, dest, sliceIndex, sliceSize);
        System.arraycopy(source, index, dest, destIndex, segmentSize);

        sliceSize  = Math.abs(index - destIndex);
        sliceIndex = forward ? index + segmentSize
                             : destIndex;

        int targetSliceIndex = forward ? index
                                       : destIndex + segmentSize;

        System.arraycopy(source, sliceIndex, dest, targetSliceIndex,
                         sliceSize);
    }
