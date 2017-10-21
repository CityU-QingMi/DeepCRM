        @Override
        public void close() throws IOException
        {
            try(Locker.Lock lock = _locker.lock())
            {
                // reduce the allocated passes
                _permits--;
                while(true)
                {
                    // Are there any future passes waiting?
                    CompletableFuture<Closeable> permit = _queue.pollFirst();
                    
                    // No - we are done
                    if (permit==null)
                        break;
                    
                    // Yes - if we can complete them, we are done
                    if (permit.complete(this))
                    {
                        _permits++;
                        break;
                    }
                    
                    // Somebody else must have completed/failed that future pass,
                    // so let's try for another.
                }
            }
        }
