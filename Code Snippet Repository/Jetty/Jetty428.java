        @Override
        public void failed(Throwable x)
        {
            List<Chunk> chunks = new ArrayList<>();
            synchronized (lock)
            {
                failure = x;
                // Transfer all chunks to fail them all.
                Chunk chunk = current;
                current = null;
                if (chunk != null)
                    chunks.add(chunk);
                chunks.addAll(DeferredContentProvider.this.chunks);
                clear();
                lock.notify();
            }
            for (Chunk chunk : chunks)
                chunk.callback.failed(x);
        }
