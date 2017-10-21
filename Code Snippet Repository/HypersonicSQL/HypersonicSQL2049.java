    public synchronized int removeFirstConsecutiveKeys(int number, int def) {

        int baseIndex = findFirstConsecutiveKeys(number);

        if (baseIndex == -1) {
            return def;
        }

        int result = keys[baseIndex];

        this.removeRange(baseIndex, baseIndex + number);

        return result;
    }
