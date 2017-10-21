    public static void initialized()
    {   
        synchronized (Log.class)
        {
            if (__initialized)
                return;
            __initialized = true;

            Boolean announce = Boolean.parseBoolean(__props.getProperty("org.eclipse.jetty.util.log.announce", "true"));

            try
            {
                Class<?> log_class = __logClass==null?null:Loader.loadClass(Log.class,__logClass);
                if (LOG == null || (log_class!=null && !LOG.getClass().equals(log_class)))
                {
                    LOG = (Logger)log_class.newInstance();
                    if(announce)
                    {
                        LOG.debug("Logging to {} via {}", LOG, log_class.getName());
                    }
                }
            }
            catch(Throwable e)
            {
                // Unable to load specified Logger implementation, default to standard logging.
                initStandardLogging(e);
            }

            if (announce && LOG!=null)
            {
                LOG.info(String.format("Logging initialized @%dms to %s",Uptime.getUptime(),LOG.getClass().getName()));
            }
        }
    }
