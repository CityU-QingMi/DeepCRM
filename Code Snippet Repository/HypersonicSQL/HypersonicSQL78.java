        public void addSession(Session session) {

            synchronized (this) {
                if (timerTask == null) {
                    start();
                }

                sessionList.add(session);
            }
        }
