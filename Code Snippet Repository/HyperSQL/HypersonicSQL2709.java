    public boolean ensureLength(long newLength) {

        try {
            if (!readOnly && file.length() < newLength) {
                file.seek(newLength - 1);
                file.writeByte(0);
            }
        } catch (IOException e) {
            logger.logWarningEvent("data file enlarge failed ", e);

            return false;
        }

        return true;
    }
