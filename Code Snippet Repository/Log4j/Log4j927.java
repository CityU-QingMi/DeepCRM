    @PluginFactory
    public static ConcurrentMap<String, Appender> createAppenders(
                @PluginElement("Appenders") final Appender[] appenders) {

        final ConcurrentMap<String, Appender> map =  new ConcurrentHashMap<>(appenders.length);

        for (final Appender appender : appenders) {
            map.put(appender.getName(), appender);
        }

        return map;
    }
