        public boolean start(Callback callback, boolean commit)
        {
            State state;
            Throwable failure;
            synchronized (this)
            {
                state = this.state;
                failure = this.failure;
                if (state == State.IDLE)
                {
                    this.state = State.WRITING;
                    this.callback = callback;
                    this.commit = commit;
                    return true;
                }
            }
            if (failure == null)
                failure = new IllegalStateException("Invalid transport state: " + state);
            callback.failed(failure);
            return false;
        }
