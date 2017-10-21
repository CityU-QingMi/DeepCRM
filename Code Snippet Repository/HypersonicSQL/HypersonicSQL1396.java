    public NodeAVLDisk(RowAVLDisk r, RowInputInterface in,
                       int id) throws IOException {

        super(r);

        iId      = id;
        iBalance = in.readInt();
        iLeft    = in.readInt();
        iRight   = in.readInt();
        iParent  = in.readInt();

        if (iLeft <= 0) {
            iLeft = NO_POS;
        }

        if (iRight <= 0) {
            iRight = NO_POS;
        }

        if (iParent <= 0) {
            iParent = NO_POS;
        }
    }
