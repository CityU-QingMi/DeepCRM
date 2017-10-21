    private static ThreadInfoFactory initFactory(final ClassLoader classLoader) {
        final ServiceLoader<ThreadInfoFactory> serviceLoader = ServiceLoader.load(ThreadInfoFactory.class, classLoader);
        ThreadInfoFactory result = null;
        try {
            final Iterator<ThreadInfoFactory> iterator = serviceLoader.iterator();
            while (result == null && iterator.hasNext()) {
                result = iterator.next();
            }
        } catch (ServiceConfigurationError | LinkageError | Exception unavailable) { // if java management classes not available
            StatusLogger.getLogger().info("ThreadDumpMessage uses BasicThreadInfoFactory: " +
                            "could not load extended ThreadInfoFactory: {}", unavailable.toString());
            result = null;
        }
        return result == null ? new BasicThreadInfoFactory() : result;
    }
