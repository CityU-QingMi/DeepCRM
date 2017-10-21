        private RecoverMisfiredJobsResult manage() {
            try {
                getLog().debug("MisfireHandler: scanning for misfires...");

                RecoverMisfiredJobsResult res = doRecoverMisfires();
                numFails = 0;
                return res;
            } catch (Exception e) {
                if(numFails % 4 == 0) {
                    getLog().error(
                        "MisfireHandler: Error handling misfires: "
                                + e.getMessage(), e);
                }
                numFails++;
            }
            return RecoverMisfiredJobsResult.NO_OP;
        }
