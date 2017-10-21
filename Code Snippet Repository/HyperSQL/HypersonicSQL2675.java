    public boolean setLength(long newLength) {

        try {
            file.setLength(newLength);
            file.seek(0);

            fileLength   = file.length();
            seekPosition = 0;

            readIntoBuffer();

            return true;
        } catch (Throwable t) {
            return false;
        }
    }
