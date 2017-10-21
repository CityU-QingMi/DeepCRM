        public long nsecToNextOperation() {

            final long now = System.nanoTime();

            long nextStartTime = expectedNextOperationNanoTime();

            boolean sendNow = true;

            if (nextStartTime > now) {
                // We are on pace. Indicate caught_up and don't send now.}
                caughtUp = true;
                sendNow = false;
            } else {
                // We are behind
                if (caughtUp) {
                    // This is the first fall-behind since we were last caught up
                    caughtUp = false;
                    catchUpStartTime = now;
                    unitsCompletedAtCatchUpStart = unitsCompleted;
                }

                // Figure out if it's time to send, per catch up throughput:
                final long unitsCompletedSinceCatchUpStart =
                        unitsCompleted - unitsCompletedAtCatchUpStart;

                nextStartTime = catchUpStartTime +
                        (long) (unitsCompletedSinceCatchUpStart / catchUpThroughputInUnitsPerNsec);

                if (nextStartTime > now) {
                    // Not yet time to send, even at catch-up throughout:
                    sendNow = false;
                }
            }

            return sendNow ? 0 : (nextStartTime - now);
        }
