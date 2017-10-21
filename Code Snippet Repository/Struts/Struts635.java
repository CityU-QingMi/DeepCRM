    public DefaultSettings() {

        ArrayList<Settings> list = new ArrayList<>();

        // stuts.properties, default.properties
        try {
            list.add(new PropertiesSettings("struts"));
        } catch (Exception e) {
            LOG.warn("DefaultSettings: Could not find or error in struts.properties", e);
        }

        delegate = new DelegatingSettings(list);

        // struts.custom.properties
        String files = delegate.get(StrutsConstants.STRUTS_CUSTOM_PROPERTIES);
        if (files != null) {
            StringTokenizer customProperties = new StringTokenizer(files, ",");

            while (customProperties.hasMoreTokens()) {
                String name = customProperties.nextToken();
                try {
                    list.add(new PropertiesSettings(name));
                } catch (Exception e) {
                    LOG.error("DefaultSettings: Could not find {}.properties. Skipping.", name);
                }
            }

            delegate = new DelegatingSettings(list);
        }
    }
