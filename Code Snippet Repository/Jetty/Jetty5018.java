        @Override
        public void succeeded()
        {
            _lock.lock();
            try
            {
                if (_state == null)
                {
                    _state = SUCCEEDED;
                    _complete.signalAll();
                }
                else
                    throw new IllegalStateException(_state);
            }
            finally
            {
                _lock.unlock();
            }
        }
