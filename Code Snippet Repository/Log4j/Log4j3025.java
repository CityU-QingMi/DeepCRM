    private URI getConfigURI(final String location) {
        try {
            String configLocation = location;
            if (configLocation == null) {
                final String[] paths = SetUtils.prefixSet(servletContext.getResourcePaths(WEB_INF), WEB_INF + "log4j2");
                LOGGER.debug("getConfigURI found resource paths {} in servletContext at [{}]", Arrays.toString(paths), WEB_INF);
                if (paths.length == 1) {
                    configLocation = paths[0];
                } else if (paths.length > 1) {
                    final String prefix = WEB_INF + "log4j2-" + this.name + ".";
                    boolean found = false;
                    for (final String str : paths) {
                        if (str.startsWith(prefix)) {
                            configLocation = str;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        configLocation = paths[0];
                    }
                }
            }
            if (configLocation != null) {
                final URL url = servletContext.getResource(configLocation);
                if (url != null) {
                    final URI uri = url.toURI();
                    LOGGER.debug("getConfigURI found resource [{}] in servletContext at [{}]", uri, configLocation);
                    return uri;
                }
            }
        } catch (final Exception ex) {
            // Just try passing the location.
        }
        if (location != null) {
            try {
                final URI correctedFilePathUri = NetUtils.toURI(location);
                LOGGER.debug("getConfigURI found [{}] in servletContext at [{}]", correctedFilePathUri, location);
                return correctedFilePathUri;
            } catch (final Exception e) {
                LOGGER.error("Unable to convert configuration location [{}] to a URI", location, e);
            }
        }
        return null;
    }
