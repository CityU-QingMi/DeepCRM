        public void closeContext() {
            try {
                if (context != null) {
                    context.close();
                }
            } catch (Throwable t) {
                getLog().warn("Failed to close InitialContext used to get a UserTransaction.", t);
            }
            context = null;
        }
