        public void start() {

            if (!logger.isLogged()) {
                return;
            }

            synchronized (this) {
                if (waiting) {
                    return;
                }

                waiting = true;
            }

            timerTask = DatabaseManager.getTimer().scheduleAfter(0, this);
        }
