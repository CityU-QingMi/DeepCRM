    public boolean ensureLength(long newLength) {

        if (newLength > maxLength) {
            return false;
        }

        while (newLength > fileLength) {
            if (!enlargeFile(newLength)) {
                return false;
            }
        }

        return true;
    }
