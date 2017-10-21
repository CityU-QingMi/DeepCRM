        @Override
        public boolean isOpen()
        {
            // Called to determine if the timer should be reset
            // True if:
            // 1. the session is still valid
            // BUT if passivated out to disk, do we really want this timer to keep going off?
            try (Lock lock = _lock.lock())
            {
                return isValid() && isResident();
            }
        }
