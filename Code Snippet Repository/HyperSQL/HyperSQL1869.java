    public boolean ensureLength(long newLength) {

        if (newLength <= initialMaxLength) {
            return store.ensureLength(newLength);
        } else if (preNio) {
            try {
                newStore(newLength);
            } catch (IOException e) {}
        }

        if (store.ensureLength(newLength)) {
            return true;
        } else if (isNio) {
            try {
                newStore(newLength);
            } catch (IOException e) {}
        }

        return store.ensureLength(newLength);
    }
