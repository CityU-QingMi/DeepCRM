    @Override
    public String toString()
    {
        ErrorCode errorCode = ErrorCode.from(error);
        return String.format("%s,%d/%s/%s",
                super.toString(),
                lastStreamId,
                errorCode != null ? errorCode.toString() : String.valueOf(error),
                tryConvertPayload());
    }
