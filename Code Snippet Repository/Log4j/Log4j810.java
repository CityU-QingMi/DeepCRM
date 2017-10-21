    @Override
    protected synchronized void writeToDestination(final byte[] bytes, final int offset, final int length) {
        try {
            if (randomAccessFile == null) {
                String fileName = getFileName();
                File file = new File(fileName);
                FileUtils.makeParentDirs(file);
                createFileAfterRollover(fileName);
            }
            randomAccessFile.write(bytes, offset, length);
            size += length;
        } catch (final IOException ex) {
            final String msg = "Error writing to RandomAccessFile " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
    }
