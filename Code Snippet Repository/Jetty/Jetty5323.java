        public void offer(Runnable task)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("{} offer {}", this, task);

            try (Locker.Lock lock = _locker.lock())
            {
                _task = task;
                _wakeup.signal();
            }
        }
