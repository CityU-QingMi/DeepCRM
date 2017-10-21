    private long getExtendLength(long position) {

        if (!extendLength) {
            return position;
        }

        int scaleUp;

        if (position < 256 * 1024) {
            scaleUp = 2;
        } else if (position < 1024 * 1024) {
            scaleUp = 6;
        } else if (position < 32 * 1024 * 1024) {
            scaleUp = 8;
        } else {
            scaleUp = 12;
        }

        position = ArrayUtil.getBinaryNormalisedCeiling(position,
                bufferScale + scaleUp);

        return position;
    }
