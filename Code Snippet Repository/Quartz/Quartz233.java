    private void shutdownFromInstantiateException(ThreadPool tp, QuartzScheduler qs, boolean tpInited, boolean qsInited) {
        try {
            if(qsInited)
                qs.shutdown(false);
            else if(tpInited)
                tp.shutdown(false);
        } catch (Exception e) {
            getLog().error("Got another exception while shutting down after instantiation exception", e);
        }
    }
