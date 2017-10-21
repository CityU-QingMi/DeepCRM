    @Override
    public String getFormattedMessage(final String[] formats) {
        if (formats != null && formats.length > 0) {
            for (int i = 0; i < formats.length; i++) {
                final String format = formats[i];
                if (Format.XML.name().equalsIgnoreCase(format)) {
                    return asXml();
                } else if (Format.FULL.name().equalsIgnoreCase(format)) {
                    return asString(Format.FULL, null);
                }
            }
            return asString(null, null);
        }
        return asString(Format.FULL, null);
    }
