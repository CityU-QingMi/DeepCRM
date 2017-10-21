    void clearStructures(StatementDMQL cs) {

        int count = cs.rangeIteratorCount;

        if (count > rangeIterators.length) {
            count = rangeIterators.length;
        }

        for (int i = 0; i < count; i++) {
            if (rangeIterators[i] != null) {
                rangeIterators[i].release();

                rangeIterators[i] = null;
            }
        }
    }
