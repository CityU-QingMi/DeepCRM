    @Override
    public PatternFormatter[] getFormatters(final LogEvent event) {
        final Marker marker = event.getMarker();
        if (marker == null) {
            return defaultFormatters;
        }
        for (final String key : formatterMap.keySet()) {
            if (marker.isInstanceOf(key)) {
                return formatterMap.get(key);
            }
        }
        return defaultFormatters;
    }
