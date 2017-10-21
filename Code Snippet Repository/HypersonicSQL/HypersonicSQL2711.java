    public void synch() {

        try {
            file.getFD().sync();
        } catch (Throwable t) {
            try {
                file.getFD().sync();
            } catch (Throwable tt) {
                logger.logSevereEvent("RA file sync error ", t);

                throw Error.error(t, ErrorCode.FILE_IO_ERROR, null);
            }
        }
    }
