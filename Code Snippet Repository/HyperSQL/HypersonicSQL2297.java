    public int countSetMatches(BitMap other) {

        int matchCount = 0;

        for (int i = 0; i < map.length; i++) {
            int matches = this.map[i] & other.map[i];

            if (matches != 0) {
                matchCount += Integer.bitCount(matches);
            }
        }

        return matchCount;
    }
