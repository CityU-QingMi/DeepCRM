        @Override
        public void run() {
            int i = 0;
            try {
                for (i=0; i < 30; ++i) {
                    logger.error("Thread: " + index + ", Test: " + i++);
                }
            } catch (final Exception ie) {
                return;
            }
            finished[index] = true;
        }
