    public boolean setLength(long newLength) {

        if (newLength > fileLength) {
            return enlargeFile(newLength);
        } else {
            try {
                seek(0);
            } catch (Throwable t) {

                //
            }

            return true;
        }
    }
