    public void synch() {

        try {
            fileDescriptor.sync();
        } catch (Throwable t) {
            try {
                fileDescriptor.sync();
            } catch (Throwable tt) {
                logger.logSevereEvent("RA file sync error ", tt);

                throw Error.error(t, ErrorCode.FILE_IO_ERROR, null);
            }
        }
    }
