    public Result createDuplicateLob(long lobID) {

        Result result = getLength(lobID);

        if (result.isError()) {
            return result;
        }

        return createDuplicateLob(lobID,
                                  ((ResultLob) result).getBlockLength());
    }
