    public static LoggerContext initialize(final String name, final ClassLoader loader, final String configLocation,
            final Object externalContext) {
        if (Strings.isBlank(configLocation)) {
            return initialize(name, loader, (URI) null, externalContext);
        }
        if (configLocation.contains(",")) {
            final String[] parts = configLocation.split(",");
            String scheme = null;
            final List<URI> uris = new ArrayList<>(parts.length);
            for (final String part : parts) {
                final URI uri = NetUtils.toURI(scheme != null ? scheme + ":" + part.trim() : part.trim());
                if (scheme == null && uri.getScheme() != null) {
                    scheme = uri.getScheme();
                }
                uris.add(uri);
            }
            return initialize(name, loader, uris, externalContext);
        }
        return initialize(name, loader, NetUtils.toURI(configLocation), externalContext);
    }
