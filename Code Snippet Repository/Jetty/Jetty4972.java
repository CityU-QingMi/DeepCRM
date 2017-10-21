        public boolean isPaused(long now)
        {
            if (pauseUntil==0)
                return false;
            if (pauseUntil>now)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("PAUSED {}",this);
                return true;
            }
            if (LOG.isDebugEnabled())
                LOG.debug("unpaused {}",this);
            pauseUntil = 0;
            return false;
        }
