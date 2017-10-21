    @Override
    protected void begin() throws SchedulerException {
        // Don't get a new UserTransaction w/o making sure we cleaned up the old 
        // one.  This is necessary because there are paths through JobRunShell.run()
        // where begin() can be called multiple times w/o complete being called in
        // between.
        cleanupUserTransaction();
        
        boolean beganSuccessfully = false;
        try {
            getLog().debug("Looking up UserTransaction.");
            ut = UserTransactionHelper.lookupUserTransaction();
            if (transactionTimeout != null) {
                ut.setTransactionTimeout(transactionTimeout);
            }

            getLog().debug("Beginning UserTransaction.");
            ut.begin();
            
            beganSuccessfully = true;
        } catch (SchedulerException se) {
            throw se;
        } catch (Exception nse) {

            throw new SchedulerException(
                    "JTAJobRunShell could not start UserTransaction.", nse);
        } finally {
            if (beganSuccessfully == false) {
                cleanupUserTransaction();
            }
        }
    }
