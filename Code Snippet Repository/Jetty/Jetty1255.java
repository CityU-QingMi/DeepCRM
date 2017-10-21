        @Override
        public void succeeded()
        {
            boolean commit;
            Callback callback = null;
            synchronized (this)
            {
                commit = this.commit;
                if (state == State.WRITING)
                {
                    callback = this.callback;
                    this.callback = null;
                    this.state = State.IDLE;
                }
            }
            if (LOG.isDebugEnabled())
                LOG.debug("HTTP2 Response #{}/{} {} {}",
                        stream.getId(), Integer.toHexString(stream.getSession().hashCode()),
                        commit ? "commit" : "flush",
                        callback == null ? "failure" : "success");
            if (callback != null)
                callback.succeeded();
        }
