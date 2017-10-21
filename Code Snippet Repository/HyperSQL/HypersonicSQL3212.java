        public void run() {

            while (keep_running) {
                long currentFree  = Runtime.getRuntime().freeMemory();
                long currentAlloc = Runtime.getRuntime().totalMemory();
                long used         = currentAlloc - currentFree;

                if (used < min) {
                    min = used;
                }

                if (used > max) {
                    max = used;
                }

                try {
                    sleep(100);
                } catch (InterruptedException E) {}
            }
        }
