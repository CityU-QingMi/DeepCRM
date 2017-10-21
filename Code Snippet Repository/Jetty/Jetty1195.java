    @Override
    public void onReset(Stream stream, ResetFrame frame)
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange == null)
            return;

        ErrorCode error = ErrorCode.from(frame.getError());
        String reason = error == null ? "reset" : error.name().toLowerCase(Locale.ENGLISH);
        exchange.getRequest().abort(new IOException(reason));
    }
