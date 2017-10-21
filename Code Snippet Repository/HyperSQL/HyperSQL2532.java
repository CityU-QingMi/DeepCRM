        public void run() {

            Thread t = new KeepAliveThread();

            t.start();

            try {
                t.join(15000);

                if (t.isAlive()) {    // If thread still running, then it's probably blocked because the ports are exhausted
                    synchronized (failCount) {
                        if (failCount == 0) {
                            failCount++;

                            fail("Keep-Alive is probably not being used");
                        }
                    }
                }
            } catch (InterruptedException ex) {}
        }
