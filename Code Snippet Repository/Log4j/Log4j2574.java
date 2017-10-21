        @Override
        public Cancellable addShutdownCallback(final Runnable callback) {
            final Cancellable cancellable = new Cancellable() {
                @Override
                public void cancel() {
                    LOGGER.debug("Cancelled shutdown callback: {}", callback);
                    CALLBACKS.remove(this);
                }

                @Override
                public void run() {
                    LOGGER.debug("Called shutdown callback: {}", callback);
                    callback.run();
                }
            };
            CALLBACKS.add(cancellable);
            return cancellable;
        }
