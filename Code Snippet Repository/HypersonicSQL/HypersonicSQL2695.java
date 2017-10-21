    public void synch() {

        boolean error    = false;
        int     errIndex = 0;

        for (int i = 0; i < buffers.length; i++) {
            try {
                buffers[i].force();
            } catch (Throwable t) {
                logger.logWarningEvent("NIO buffer force error: pos "
                                       + i * largeBufferSize + " ", t);

                if (!error) {
                    errIndex = i;
                }

                error = true;
            }
        }

        if (error) {
            for (int i = errIndex; i < buffers.length; i++) {
                try {
                    buffers[i].force();
                } catch (Throwable t) {
                    logger.logWarningEvent("NIO buffer force error "
                                           + i * largeBufferSize + " ", t);
                }
            }
        }

        try {
            fileDescriptor.sync();

            buffersModified = false;
        } catch (Throwable t) {
            logger.logSevereEvent("NIO RA file sync error ", t);

            throw Error.error(t, ErrorCode.FILE_IO_ERROR, null);
        }
    }
