        public boolean checkTimeout() {

            if (currentTimeout == 0) {
                return true;
            }

            if (aborted || actionTimestamp != Session.this.actionTimestamp) {
                actionTimestamp = 0;
                currentTimeout  = 0;
                aborted         = false;

                return true;
            }

            --currentTimeout;

            if (currentTimeout <= 0) {
                currentTimeout = 0;
                aborted        = true;

                database.txManager.resetSession(
                    null, Session.this, TransactionManager.resetSessionAbort);

                return true;
            }

            return false;
        }
