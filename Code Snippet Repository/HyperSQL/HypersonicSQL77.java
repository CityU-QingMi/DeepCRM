        public void stop() {

            synchronized (this) {
                if (timerTask == null) {
                    return;
                }

                HsqlTimer.cancel(timerTask);
                sessionList.clear();

                timerTask   = null;
                sessionList = null;
            }
        }
