    public void close() {

        stop();

        if (isClosed) {
            return;
        }

        try {
            synchronized (fileStreamOut) {
                finishStream();
                forceSync();
                fileStreamOut.close();

                outDescriptor = null;
                isClosed      = true;
            }
        } catch (IOException e) {
            throw Error.error(ErrorCode.FILE_IO_ERROR);
        }

        byteCount = 0;
        lineCount = 0;
    }
