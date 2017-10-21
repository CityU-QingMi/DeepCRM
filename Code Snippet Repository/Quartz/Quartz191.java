        @Override
        protected void finalize() throws Throwable {
            try {
                if (context != null) {
                    getLog().warn("UserTransaction was never returned to the UserTransactionHelper.");
                    closeContext();
                }
            } finally {
                super.finalize();
            }
        }
