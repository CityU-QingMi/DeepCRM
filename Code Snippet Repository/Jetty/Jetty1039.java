    @Override
    public boolean close(int error, String reason, Callback callback)
    {
        while (true)
        {
            CloseState current = closed.get();
            switch (current)
            {
                case NOT_CLOSED:
                {
                    if (closed.compareAndSet(current, CloseState.LOCALLY_CLOSED))
                    {
                        byte[] payload = null;
                        if (reason != null)
                        {
                            // Trim the reason to avoid attack vectors.
                            reason = reason.substring(0, Math.min(reason.length(), 32));
                            payload = reason.getBytes(StandardCharsets.UTF_8);
                        }
                        GoAwayFrame frame = new GoAwayFrame(lastStreamId.get(), error, payload);
                        control(null, callback, frame);
                        return true;
                    }
                    break;
                }
                default:
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Ignoring close {}/{}, already closed", error, reason);
                    callback.succeeded();
                    return false;
                }
            }
        }
    }
