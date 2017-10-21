        @Override
        public void failed(Throwable cause)
        {
            _lock.lock();
            try
            {
                if (_state == null)
                {
                    if (cause==null)
                        _state=FAILED;
                    else if (cause instanceof BlockerTimeoutException)
                        // Not this blockers timeout
                        _state=new IOException(cause);
                    else 
                        _state=cause;
                    _complete.signalAll();
                }
                else if (_state instanceof BlockerTimeoutException)
                {
                    // Failure arrived late, block() already
                    // modified the state, nothing more to do.
                }
                else
                {
                    cause.printStackTrace(System.err);
                    throw new IllegalStateException(_state);
                }
            }
            finally
            {
                _lock.unlock();
            }
        }
