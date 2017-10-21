    public int getCommonElementCount(Set other) {

        int count = 0;

        for (int i = 0, size = size(); i < size; i++) {
            if (other.contains(objectKeyTable[i])) {
                count++;
            }
        }

        return count;
    }
