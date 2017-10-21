        @Override
        public void put(final E e) throws InterruptedException {
            int idleCounter = 0;
            do {
                if (offer(e)) {
                    return;
                }
                idleCounter = waitStrategy.idle(idleCounter);
            } while (!Thread.interrupted()); //clear interrupted flag
            throw new InterruptedException();
        }
