    public void resetCapacity(int newCapacity,
                              int newPolicy) throws IllegalArgumentException {

        if (newCapacity != 0 && hashIndex.elementCount > newCapacity) {
            int surplus = hashIndex.elementCount - newCapacity;

            surplus += (surplus >> 5);

            if (surplus > hashIndex.elementCount) {
                surplus = hashIndex.elementCount;
            }

            clear(surplus, (surplus >> 6));
        }

        if (newCapacity != 0 && newCapacity < threshold) {
            rehash(newCapacity);

            if (newCapacity < hashIndex.elementCount) {
                newCapacity = maxCapacity;
            }
        }

        this.maxCapacity = newCapacity;
        this.purgePolicy = newPolicy;
    }
