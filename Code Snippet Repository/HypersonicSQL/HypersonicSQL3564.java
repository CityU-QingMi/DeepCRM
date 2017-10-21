    public static BitSet getJavaBitSet(BinaryData data) {

        int    bits  = (int) data.bitLength(null);
        BitSet bs    = new BitSet(bits);
        byte[] bytes = data.getBytes();

        for (int i = 0; i < bits; i++) {
            boolean set = BitMap.isSet(bytes, i);

            if (set) {
                bs.set(i);
            }
        }

        return bs;
    }
