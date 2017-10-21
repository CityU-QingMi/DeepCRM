        protected boolean tryReleaseShared(int requestedCount) {

            final int     newCount = Math.max(0, requestedCount);
            final boolean result   = (newCount == 0);

            for (;;) {
                if (compareAndSetState(getState(), newCount)) {
                    return result;
                }
            }
        }
