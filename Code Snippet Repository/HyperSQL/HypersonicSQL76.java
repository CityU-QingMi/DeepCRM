        public void run() {

            try {
                for (int i = sessionList.size() - 1; i >= 0; i--) {
                    Session session = (Session) sessionList.get(i);

                    if (session.isClosed()) {
                        synchronized (this) {
                            sessionList.remove(i);
                        }

                        continue;
                    }

                    boolean result = session.timeoutManager.checkTimeout();

                    if (result) {
                        synchronized (this) {
                            sessionList.remove(i);
                        }
                    }
                }
            } catch (Throwable e) {

                // ignore exceptions
                // may be InterruptedException or IOException
            }
        }
