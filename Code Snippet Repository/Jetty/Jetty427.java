        @Override
        public void succeeded()
        {
            Chunk chunk;
            synchronized (lock)
            {
                chunk = current;
                if (chunk != null)
                {
                    --size;
                    lock.notify();
                }
            }
            if (chunk != null)
                chunk.callback.succeeded();
        }
