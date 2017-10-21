    public synchronized String toString() {

        try {
            checkFreed();
        } catch (IOException ex) {
            throw new RuntimeException(ex.toString());
        }

        return new String(buf, 0, count);
    }
