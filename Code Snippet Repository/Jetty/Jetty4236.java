        public boolean isRateExceeded(long now)
        {
            final long last;
            synchronized (this)
            {
                last = _timestamps[_next];
                _timestamps[_next] = now;
                _next = (_next + 1) % _timestamps.length;
            }

            return last != 0 && (now - last) < 1000L;
        }
