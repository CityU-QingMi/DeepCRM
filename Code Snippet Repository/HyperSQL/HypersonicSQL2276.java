    public int countSetBits() {

        int setCount = 0;

        for (int windex = 0; windex < limitPos / Integer.SIZE; windex++) {
            int word = map[windex];

            if (word == 0) {
                continue;
            }

            if (word == -1) {
                setCount += Integer.SIZE;

                continue;
            }

            setCount += Integer.bitCount(word);
        }

        if (limitPos % Integer.SIZE != 0) {
            int maskend = 0x80000000 >> ((limitPos - 1) & 0x1F);
            int word    = map[limitPos / Integer.SIZE] & maskend;

            setCount += Integer.bitCount(word);
        }

        return setCount;
    }
