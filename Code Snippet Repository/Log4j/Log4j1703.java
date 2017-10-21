        @Override
        public void run() {
            final Thread thread = Thread.currentThread();

            try {
                writer(lock, logEventCount, thread.getName(), createOnDemand, true);
            } catch (final Exception e) {
                exceptionRef[0] = e;
                Throwables.rethrow(e);
            }
        }
