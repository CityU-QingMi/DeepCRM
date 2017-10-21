        @Override
        public ByteBuffer next()
        {
            synchronized (lock)
            {
                Chunk chunk = current = chunks.poll();
                if (chunk == CLOSE)
                {
                    // Slow path: reinsert the CLOSE chunk
                    // so that hasNext() works correctly.
                    chunks.offerFirst(CLOSE);
                    throw new NoSuchElementException();
                }
                return chunk == null ? null : chunk.buffer;
            }
        }
