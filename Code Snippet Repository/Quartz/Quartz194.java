    @Override
    public void destroy() {

        if (!performShutdown) {
            return;
        }

        try {
            if (scheduler != null) {
                scheduler.shutdown(waitOnShutdown);
            }
        } catch (Exception e) {
            log("Quartz Scheduler failed to shutdown cleanly: " + e.toString());
            e.printStackTrace();
        }

        log("Quartz Scheduler successful shutdown.");
    }
