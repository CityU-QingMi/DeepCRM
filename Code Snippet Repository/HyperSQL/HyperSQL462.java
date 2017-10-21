    public NodeAVLDiskLarge(RowAVLDisk r, RowInputInterface in,
                            int id) throws IOException {

        super(r);

        iId = id;

        int ext = in.readInt();

        iBalance = (byte) ext;
        iLeft    = in.readInt() & 0xffffffffL;
        iRight   = in.readInt() & 0xffffffffL;
        iParent  = in.readInt() & 0xffffffffL;

        if (ext > 0xff) {
            iParent |= (((long) ext << 8) & 0xff00000000L);
            iLeft   |= (((long) ext << 16) & 0xff00000000L);
            iRight  |= (((long) ext << 24) & 0xff00000000L);
        }

        if (iLeft == 0) {
            iLeft = NO_POS;
        }

        if (iRight == 0) {
            iRight = NO_POS;
        }

        if (iParent == 0) {
            iParent = NO_POS;
        }
    }
