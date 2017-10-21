        @Override
        public void failed(Throwable failure)
        {
            boolean commit;
            Callback callback = null;
            synchronized (this)
            {
                commit = this.commit;
                // Only fail pending writes, as we
                // may need to write an error page.
                if (state == State.WRITING)
                {
                    this.state = State.FAILED;
                    this.failure = failure;
                    callback = this.callback;
                    this.callback = null;
                }
            }
            if (LOG.isDebugEnabled())
                LOG.debug(String.format("HTTP2 Response #%d/%h failed to %s", stream.getId(), stream.getSession(), commit ? "commit" : "flush"), failure);
            if (callback != null)
                callback.failed(failure);
        }
