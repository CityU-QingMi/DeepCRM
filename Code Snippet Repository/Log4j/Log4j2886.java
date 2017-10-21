    private void shiftKeys(int pos) {
        // Shift entries with the same hash.
        int last, slot;
        K curr;
        final K[] myKeys = this.keys;
        for (;;) {
            pos = ((last = pos) + 1) & mask;
            for (;;) {
                if (((curr = myKeys[pos]) == null)) {
                    myKeys[last] = (null);
                    values[last] = null;
                    return;
                }
                slot = HashCommon.mix(curr.hashCode()) & mask;
                if (last <= pos ? (last >= slot || slot > pos) : (last >= slot && slot > pos)) {
                    break;
                }
                pos = (pos + 1) & mask;
            }
            myKeys[last] = curr;
            values[last] = values[pos];
        }
    }
