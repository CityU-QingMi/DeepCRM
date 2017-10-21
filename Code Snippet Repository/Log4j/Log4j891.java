        @Override
        public boolean offer(final E e, final long timeout, final TimeUnit unit) throws InterruptedException {
            int idleCounter = 0;
            final long timeoutNanos = System.nanoTime() + unit.toNanos(timeout);
            do {
                if (offer(e)) {
                    return true;
                } else if (System.nanoTime() - timeoutNanos > 0) {
                    return false;
                }
                idleCounter = waitStrategy.idle(idleCounter);
            } while (!Thread.interrupted()); //clear interrupted flag
            throw new InterruptedException();
        }
