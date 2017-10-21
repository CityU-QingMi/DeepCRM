    protected boolean test(Statement aStatement) {

        if (waiter == null) {
            try {

                //System.err.println("Sleeping for " + sleepTime + " ms.");
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                throw new RuntimeException("Test sleep interrupted", ie);
            }
        } else {
            waiter.waitFor(enforceSequence);
        }

        return true;
    }
