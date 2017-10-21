        @Override
        public List<OperableTrigger> acquireNextTriggers(long noLaterThan, int maxCount, long timeWindow) {
            List<OperableTrigger> nextTriggers = super.acquireNextTriggers(noLaterThan, maxCount, timeWindow);
            try {
                // Wait just a bit for hopefully having a context switch leading to the race condition
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            return nextTriggers;
        }
