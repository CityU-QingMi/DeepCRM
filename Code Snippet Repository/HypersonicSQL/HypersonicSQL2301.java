    public int get(int pos) {

        if (pos >= limitPos) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }

        int windex = pos >> 5;
        int mask   = 0x80000000 >>> (pos & 0x1F);
        int word   = map[windex];

        return (word & mask) == 0 ? 0
                                  : 1;
    }
