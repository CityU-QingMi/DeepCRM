        public void block() throws IOException
        {
            long idle = getIdleTimeout();
            _lock.lock();
            try
            {
                while (_state == null)
                {
                    if (idle > 0)
                    {
                        // Waiting here may compete with the idle timeout mechanism,
                        // so here we wait a little bit longer to favor the normal
                        // idle timeout mechanism that will call failed(Throwable).
                        long excess = Math.min(idle / 2, 1000);
                        if (!_complete.await(idle + excess, TimeUnit.MILLISECONDS))
                        {
                            // Method failed(Throwable) has not been called yet,
                            // so we will synthesize a special TimeoutException.
                            _state = new BlockerTimeoutException();
                        }
                    }
                    else
                    {
                        _complete.await();
                    }
                }

                if (_state == SUCCEEDED)
                    return;
                if (_state == IDLE)
                    throw new IllegalStateException("IDLE");
                if (_state instanceof IOException)
                    throw (IOException)_state;
                if (_state instanceof CancellationException)
                    throw (CancellationException)_state;
                if (_state instanceof RuntimeException)
                    throw (RuntimeException)_state;
                if (_state instanceof Error)
                    throw (Error)_state;
                throw new IOException(_state);
            }
            catch (final InterruptedException e)
            {
                throw new InterruptedIOException();
            }
            finally
            {
                _lock.unlock();
            }
        }
