    private int copy(int pageOffset) throws IOException {

        if (bitMap.set(pageOffset) == 1) {
            return 0;
        }

        long position  = (long) pageOffset * pageSize;
        int  readSize  = pageSize;
        int  writeSize = buffer.length;

        if (maxSize - position < pageSize) {
            readSize = (int) (maxSize - position);
        }

        if (dest == null) {
            open();
        }

        long writePos = dest.length();

        try {
            byteArrayOutputStream.reset();

            if (readSize < pageSize) {
                byteArrayOutputStream.fill(0, buffer.length);
                byteArrayOutputStream.reset();
            }

            byteArrayOutputStream.writeInt(pageSize);
            byteArrayOutputStream.writeLong(position);
            source.seek(position);
            source.read(buffer, headerSize, readSize);
            dest.seek(writePos);
            dest.write(buffer, 0, writeSize);

            savedLength = writePos + writeSize;

            return 1;
        } catch (Throwable t) {
            bitMap.unset(pageOffset);
            dest.seek(0);
            dest.setLength(writePos);
            close();
            database.logger.logSevereEvent("shadow backup failure pos "
                                           + position + " " + readSize, t);

            throw JavaSystem.toIOException(t);
        }
    }
