    public Result getLength(long lobID) {

        writeLock.lock();

        try {
            Object[] data = getLobHeader(lobID);

            if (data == null) {
                throw Error.error(ErrorCode.X_0F502);
            }

            long length = ((Long) data[LOB_IDS.LOB_LENGTH]).longValue();
            int  type   = ((Integer) data[LOB_IDS.LOB_TYPE]).intValue();

            return ResultLob.newLobSetResponse(lobID, length);
        } catch (HsqlException e) {
            return Result.newErrorResult(e);
        } finally {
            writeLock.unlock();
        }
    }
