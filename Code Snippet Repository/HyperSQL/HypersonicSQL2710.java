    public boolean setLength(long newLength) {

        try {
            file.setLength(newLength);

            return true;
        } catch (Throwable t) {
            return false;
        }
    }
