        @Override
        public boolean isRateExceeded(long now)
        {
            // rate limit is never exceeded, but we keep track of the request timestamps
            // so that we know whether there was recent activity on this tracker
            // and whether it should be expired
            synchronized (this)
            {
                _timestamps[_next] = now;
                _next = (_next + 1) % _timestamps.length;
            }

            return false;
        }
