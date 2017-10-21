    public ResourceBundle getResourceBundle() {
        if (bundle != null) {
            return bundle;
        }
        String name = logger.getName();
        final ConcurrentMap<String, Logger> loggers = getLoggersMap(logger.getContext());
        while ((name = NameUtil.getSubName(name)) != null) {
            final Logger subLogger = loggers.get(name);
            if (subLogger != null) {
				final ResourceBundle rb = subLogger.bundle;
                if (rb != null) {
                    return rb;
                }
            }
        }
        return null;
    }
