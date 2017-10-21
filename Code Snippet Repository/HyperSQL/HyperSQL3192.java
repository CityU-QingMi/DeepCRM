        public void run() {

            try {
                directRefreshTree();
            } catch (RuntimeException re) {
                CommonSwing.errorMessage(re);

                throw re;
            } finally {
                setWaiting(null);
            }
        }
