    private static void initStandardLogging(Throwable e)
    {
        Class<?> log_class;
        if(e != null && __ignored)
        {
            e.printStackTrace(System.err);
        }

        if (LOG == null)
        {
            log_class = StdErrLog.class;
            LOG = new StdErrLog();

            Boolean announce = Boolean.parseBoolean(__props.getProperty("org.eclipse.jetty.util.log.announce", "true"));
            if(announce)
            {
                LOG.debug("Logging to {} via {}", LOG, log_class.getName());
            }
        }
    }
