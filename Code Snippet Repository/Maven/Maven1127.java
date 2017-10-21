        public void run() {
            try
            {
                latch.await();
            }
            catch (InterruptedException ignore)
            {
                // Test may fail if we get interrupted
            }
            session = defaultLegacySupport.getSession();
        }
