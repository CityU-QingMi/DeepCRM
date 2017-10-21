    public boolean ensureLength(long newLength) {

        if (newLength <= fileLength) {
            return true;
        }

        try {
            extendLength(newLength);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
