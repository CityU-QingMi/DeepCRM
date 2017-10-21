        private boolean manage() {
            boolean res = false;
            try {

                res = doCheckin();

                numFails = 0;
                getLog().debug("ClusterManager: Check-in complete.");
            } catch (Exception e) {
                if(numFails % 4 == 0) {
                    getLog().error(
                        "ClusterManager: Error managing cluster: "
                                + e.getMessage(), e);
                }
                numFails++;
            }
            return res;
        }
