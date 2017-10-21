    public static void configure(PlexusContainer pc, String file) throws PlexusConfigurationResourceException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        if (is == null) {
            if (LOG.isInfoEnabled()) {
        	LOG.info("Could not find " + file + ", skipping");
            }
            is = new ByteArrayInputStream("<plexus><components></components></plexus>".getBytes());
        }
        pc.setConfigurationResource(new InputStreamReader(is));
    }
