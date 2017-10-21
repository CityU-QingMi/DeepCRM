    @Override
    public String getFormattedMessage(final String[] formats) {
        if (formats == null || formats.length == 0) {
            return asString();
        }
        for (int i = 0; i < formats.length; i++) {
            final MapFormat mapFormat = MapFormat.lookupIgnoreCase(formats[i]);
            if (mapFormat != null) {
                return format(mapFormat, new StringBuilder()).toString();
            }
        }
        return asString();

    }
