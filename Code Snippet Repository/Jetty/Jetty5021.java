        @Override
        public void close()
        {
            _lock.lock();
            try
            {
                if (_state == IDLE)
                    throw new IllegalStateException("IDLE");
                if (_state == null)
                    notComplete(this);
            }
            finally
            {
                try 
                {
                    // If the blocker timed itself out, remember the state
                    if (_state instanceof BlockerTimeoutException)
                        // and create a new Blocker
                        _blocker=new Blocker();
                    else
                        // else reuse Blocker
                        _state = IDLE;
                    _idle.signalAll();
                    _complete.signalAll();
                } 
                finally 
                {
                    _lock.unlock();
                }
            }
        }
