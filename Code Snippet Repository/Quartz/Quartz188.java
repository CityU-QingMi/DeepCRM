    @Override
    protected void complete(boolean successfulExecution)
        throws SchedulerException {
        if (ut == null) {
            return;
        }

        try {
            try {
                if (ut.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
                    getLog().debug("UserTransaction marked for rollback only.");
                    successfulExecution = false;
                }
            } catch (SystemException e) {
                throw new SchedulerException(
                        "JTAJobRunShell could not read UserTransaction status.", e);
            }
    
            if (successfulExecution) {
                try {
                    getLog().debug("Committing UserTransaction.");
                    ut.commit();
                } catch (Exception nse) {
                    throw new SchedulerException(
                            "JTAJobRunShell could not commit UserTransaction.", nse);
                }
            } else {
                try {
                    getLog().debug("Rolling-back UserTransaction.");
                    ut.rollback();
                } catch (Exception nse) {
                    throw new SchedulerException(
                            "JTAJobRunShell could not rollback UserTransaction.",
                            nse);
                }
            }
        } finally {
            cleanupUserTransaction();
        }
    }
