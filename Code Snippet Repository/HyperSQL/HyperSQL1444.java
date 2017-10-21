    private int setOrUnsetRange(int pos, int count, boolean set) {

        if (count == 0) {
            return 0;
        }

        ensureCapacity(pos + count);

        int windex    = pos >> 5;
        int windexend = (pos + count - 1) >> 5;
        int mask      = 0xffffffff >>> (pos & 0x1F);
        int maskend   = 0x80000000 >> ((pos + count - 1) & 0x1F);
        int word;
        int setCount;

        if (windex == windexend) {
            mask &= maskend;
        }

        word     = map[windex];
        setCount = Integer.bitCount(word & mask);

        if (set) {
            map[windex] = (word | mask);
        } else {
            mask        = ~mask;
            map[windex] = (word & mask);
        }

        if (windex != windexend) {
            word     = map[windexend];
            setCount += Integer.bitCount(word & maskend);

            if (set) {
                map[windexend] = (word | maskend);
            } else {
                maskend        = ~maskend;
                map[windexend] = (word & maskend);
            }

            for (int i = windex + 1; i < windexend; i++) {
                setCount += Integer.bitCount(map[i]);
                map[i]   = set ? 0xffffffff
                               : 0;
            }
        }

        return set ? count - setCount
                   : setCount;
    }
