    protected void setFileModified() {

        try {
            if (!fileModified) {

                // unset saved flag;
                int flags = getFlags();

                flags = BitMap.unset(flags, FLAG_ISSAVED);

                setFlags(flags);
                logDetailEvent("setFileModified flag set ");

                fileModified = true;
            }
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.setFileModified", t);

            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
