    public synchronized int findFirstConsecutiveKeys(int number) {

        int baseIndex = -1;

        if (count == 0) {
            return -1;
        }

        if (!sorted) {
            fastQuickSort();
        }

        if (number == 1) {
            return 0;
        }

        for (int i = 1; i < count; i++) {
            if (keys[i - 1] == keys[i] - 1) {
                if (baseIndex == -1) {
                    baseIndex = i - 1;
                }

                if (i - baseIndex + 1 == number) {
                    return baseIndex;
                }
            } else {
                baseIndex = -1;
            }
        }

        return -1;
    }
