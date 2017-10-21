        @Override
        public E take() throws InterruptedException {
            int idleCounter = 100;
            do {
                final E result = relaxedPoll();
                if (result != null) {
                    return result;
                }
                idleCounter = waitStrategy.idle(idleCounter);
            } while (!Thread.interrupted()); //clear interrupted flag
            throw new InterruptedException();
        }
