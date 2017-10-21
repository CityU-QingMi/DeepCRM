    private void extendLength(long position) throws IOException {

        long newSize = getExtendLength(position);

        if (newSize > fileLength) {
            try {
                file.seek(newSize - 1);
                file.write(0);

                fileLength = newSize;
            } catch (IOException e) {
                logger.logWarningEvent("data file enlarge failed ", e);

                throw e;
            }
        }
    }
