        public void run() {

            Runnable task;

            try {
                while (!isShutdown) {
                    synchronized (queue) {
                        task = (Runnable) queue.getFirst();
                    }

                    if (task == SHUTDOWNTASK) {
                        isShutdown = true;

                        synchronized (queue) {
                            queue.clear();
                        }

                        break;
                    } else if (task != null) {
                        task.run();

                        task = null;
                    } else {
                        break;
                    }
                }
            } finally {
                clearThread();
            }
        }
