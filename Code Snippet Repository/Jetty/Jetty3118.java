        public CompletableFuture<Closeable> acquire()
        {
            try(Locker.Lock lock = _locker.lock())
            {
                // Do we have available passes?
                if (_permits<_limit)
                {
                    // Yes - increment the allocated passes
                    _permits++;
                    // return the already completed future
                    return _permitted; // TODO is it OK to share/reuse this?
                }
                
                // No pass available, so queue a new future 
                CompletableFuture<Closeable> pass = new CompletableFuture<Closeable>();
                _queue.addLast(pass);
                return pass;
            }
        }
