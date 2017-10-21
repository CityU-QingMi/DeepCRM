        @Override
        public void run() {
            while (!shutdown) {
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                // for running from IDE
                final File file = new File("target/test-classes/reconfiguration-deadlock.xml");
                if (file.exists()) {
                    file.setLastModified(System.currentTimeMillis());
                }
            }
        }
