    public void shutdown() {
        shutdown = true;
        
        if (misfireHandler != null) {
            misfireHandler.shutdown();
            try {
                misfireHandler.join();
            } catch (InterruptedException ignore) {
            }
        }

        if (clusterManagementThread != null) {
            clusterManagementThread.shutdown();
            try {
                clusterManagementThread.join();
            } catch (InterruptedException ignore) {
            }
        }

        try {
            DBConnectionManager.getInstance().shutdown(getDataSource());
        } catch (SQLException sqle) {
            getLog().warn("Database connection shutdown unsuccessful.", sqle);
        }        
        
        getLog().debug("JobStore background threads shutdown.");
    }
