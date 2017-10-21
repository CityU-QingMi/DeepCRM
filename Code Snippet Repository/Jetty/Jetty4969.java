    private void createWatchService () throws IOException
    {
        //create a watch service
        this.watchService = FileSystems.getDefault().newWatchService();

        WatchEvent.Modifier modifiers[] = null;
        boolean nativeService = true;
        // Try to determine native behavior
        // See http://stackoverflow.com/questions/9588737/is-java-7-watchservice-slow-for-anyone-else
        try
        {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> pollingWatchServiceClass = Class.forName("sun.nio.fs.PollingWatchService",false,cl);
            if (pollingWatchServiceClass.isAssignableFrom(this.watchService.getClass()))
            {
                nativeService = false;
                LOG.info("Using Non-Native Java {}",pollingWatchServiceClass.getName());
                Class<?> c = Class.forName("com.sun.nio.file.SensitivityWatchEventModifier");
                Field f = c.getField("HIGH");
                modifiers = new WatchEvent.Modifier[]
                    {
                        (WatchEvent.Modifier)f.get(c)
                    };
            }
        }
        catch (Throwable t)
        {
            // Unknown JVM environment, assuming native.
            LOG.ignore(t);
        }

        this.watchModifiers = modifiers;
        this.nativeWatchService = nativeService;
    }
