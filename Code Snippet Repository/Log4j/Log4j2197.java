        @Override
        public void run() {
            log.info(threads.size());
            try {
                for (int i = 0; i < 64; i++) {
                    log.info("First message.");
                    log.info("Second message.");
                }
            } finally {
                threads.remove(this);
            }
        }
