    synchronized public void release(long pos, int rowSize) {

        int rowUnits = rowSize / scale;

        isModified = true;

        releaseCount++;

        if (lookup.size() == capacity) {
            resetList();
        }

        if (pos + rowUnits >= Integer.MAX_VALUE) {
            return;
        }

        lookup.add(pos, rowUnits);
    }
