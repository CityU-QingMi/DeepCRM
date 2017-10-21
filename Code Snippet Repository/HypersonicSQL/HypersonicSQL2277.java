    public int countSetBitsEnd() {

        int count  = 0;
        int windex = (limitPos / Integer.SIZE) - 1;

        for (; windex >= 0; windex--) {
            if (map[windex] == 0xffffffff) {
                count += Integer.SIZE;

                continue;
            }

            int val = countSetBitsEnd(map[windex]);

            count += val;

            break;
        }

        return count;
    }
