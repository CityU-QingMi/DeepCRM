    @Override
    public boolean abort(HttpExchange exchange, Throwable requestFailure, Throwable responseFailure)
    {
        boolean aborted = super.abort(exchange, requestFailure, responseFailure);
        if (aborted)
        {
            Stream stream = getStream();
            if (stream != null)
                stream.reset(new ResetFrame(stream.getId(), ErrorCode.CANCEL_STREAM_ERROR.code), Callback.NOOP);
        }
        return aborted;
    }
