    public synchronized boolean compactLookupAsIntervals() {

        if (size() == 0) {
            return false;
        }

        setKeysSearchTarget();

        if (!sorted) {
            fastQuickSort();
        }

        int base = 0;

        for (int i = 1; i < count; i++) {
            long limit = keys[base] + values[base];

            if (limit == keys[i]) {
                values[base] += values[i];    // base updated
            } else {
                base++;

                keys[base]   = keys[i];
                values[base] = values[i];
            }
        }

        for (int i = base + 1; i < count; i++) {
            keys[i]   = 0;
            values[i] = 0;
        }

        if (count != base + 1) {
            setSize(base + 1);

            return true;
        }

        return false;
    }
