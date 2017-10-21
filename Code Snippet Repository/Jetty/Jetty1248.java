        private boolean onIdleTimeout(Throwable failure)
        {
            boolean result;
            Callback callback = null;
            synchronized (this)
            {
                // Ignore idle timeouts if not writing,
                // as the application may be suspended.
                result = state == State.WRITING;
                if (result)
                {
                    this.state = State.TIMEOUT;
                    callback = this.callback;
                    this.callback = null;
                    this.failure = failure;
                }
            }
            if (LOG.isDebugEnabled())
                LOG.debug(String.format("HTTP2 Response #%d/%h idle timeout", stream.getId(), stream.getSession()), failure);
            if (result)
                callback.failed(failure);
            return result;
        }
