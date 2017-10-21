        @Override
        public E poll(final long timeout, final TimeUnit unit) throws InterruptedException {
            int idleCounter = 0;
            final long timeoutNanos = System.nanoTime() + unit.toNanos(timeout);
            do {
                final E result = poll();
                if (result != null) {
                    return result;
                } else if (System.nanoTime() - timeoutNanos > 0) {
                    return null;
                }
                idleCounter = waitStrategy.idle(idleCounter);
            } while (!Thread.interrupted()); //clear interrupted flag
            throw new InterruptedException();
        }
