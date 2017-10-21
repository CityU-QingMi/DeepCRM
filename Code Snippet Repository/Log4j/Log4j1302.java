    @Override
    public String lookup(final LogEvent event, final String key) {
        final boolean isMapMessage = event != null && event.getMessage() instanceof StringMapMessage;
        if (map == null && !isMapMessage) {
            return null;
        }
        if (map != null && map.containsKey(key)) {
            final String obj = map.get(key);
            if (obj != null) {
                return obj;
            }
        }
        if (isMapMessage) {
            return ((StringMapMessage) event.getMessage()).get(key);
        }
        return null;
    }
