    public PropertiesSettings(String name) {
        
        URL settingsUrl = ClassLoaderUtil.getResource(name + ".properties", getClass());
        
        if (settingsUrl == null) {
            LOG.debug("{}.properties missing", name);
            settings = new LocatableProperties();
            return;
        }
        
        settings = new LocatableProperties(new LocationImpl(null, settingsUrl.toString()));

        // Load settings
        try (InputStream in = settingsUrl.openStream()) {
            settings.load(in);
        } catch (IOException e) {
            throw new StrutsException("Could not load " + name + ".properties: " + e, e);
        }
    }
