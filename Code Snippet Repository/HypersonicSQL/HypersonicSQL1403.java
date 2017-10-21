    public void write(RowOutputInterface out) {

        out.writeInt(iBalance);
        out.writeInt((iLeft == NO_POS) ? 0
                                       : iLeft);
        out.writeInt((iRight == NO_POS) ? 0
                                        : iRight);
        out.writeInt((iParent == NO_POS) ? 0
                                         : iParent);
    }
