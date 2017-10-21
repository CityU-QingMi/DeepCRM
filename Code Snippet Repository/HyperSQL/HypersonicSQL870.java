        void startTimeout(int timeout) {

            aborted = false;

            if (timeout == 0) {
                return;
            }

            currentTimeout  = timeout;
            actionTimestamp = Session.this.actionTimestamp;

            database.timeoutRunner.addSession(Session.this);
        }
