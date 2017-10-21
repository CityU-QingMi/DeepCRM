        @Override
        public void run() {
            started.countDown();
            try {
                keepAlive.await();
            } catch (final InterruptedException e) {
                // ignored
            }
            finished = true;
        }
