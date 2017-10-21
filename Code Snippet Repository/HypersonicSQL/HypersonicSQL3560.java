    int getRightTrimSize(BinaryData data) {

        int    i     = (int) data.bitLength(null) - 1;
        byte[] bytes = data.getBytes();

        for (; i >= 0; i--) {
            if (BitMap.isSet(bytes, i)) {
                break;
            }
        }

        return i + 1;
    }
