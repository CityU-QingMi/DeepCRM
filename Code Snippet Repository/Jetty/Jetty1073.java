    private void onData(DataFrame frame, Callback callback)
    {
        if (getRecvWindow() < 0)
        {
            // It's a bad client, it does not deserve to be
            // treated gently by just resetting the stream.
            session.close(ErrorCode.FLOW_CONTROL_ERROR.code, "stream_window_exceeded", Callback.NOOP);
            callback.failed(new IOException("stream_window_exceeded"));
            return;
        }

        // SPEC: remotely closed streams must be replied with a reset.
        if (isRemotelyClosed())
        {
            reset(new ResetFrame(streamId, ErrorCode.STREAM_CLOSED_ERROR.code), Callback.NOOP);
            callback.failed(new EOFException("stream_closed"));
            return;
        }

        if (isReset())
        {
            // Just drop the frame.
            callback.failed(new IOException("stream_reset"));
            return;
        }

        if (updateClose(frame.isEndStream(), false))
            session.removeStream(this);
        notifyData(this, frame, callback);
    }
