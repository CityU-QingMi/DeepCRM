        public void clear()
        {
            if (_space == null)
            {
                queueClear();
            }
            else
            {
                int s = _space.getAndSet(0);
                while (s-- > 0)
                {
                    if (queuePoll() == null)
                        _space.incrementAndGet();
                }
            }
        }
