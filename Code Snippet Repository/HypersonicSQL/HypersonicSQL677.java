    public synchronized boolean keepInMemory(boolean keep) {

        if (!isInMemory) {
            return false;
        }

        if (keep) {
            keepCount++;
        } else {
            keepCount--;

            if (keepCount < 0) {
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "RowAVLDisk - keep count");
            }
        }

        return true;
    }
