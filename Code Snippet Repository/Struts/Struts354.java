    @Inject(value = StrutsConstants.STRUTS_CUSTOM_I18N_RESOURCES, required = false)
    public void setCustomI18NResources(String bundles) {
        if (bundles != null && bundles.length() > 0) {
            StringTokenizer customBundles = new StringTokenizer(bundles, ", ");

            while (customBundles.hasMoreTokens()) {
                String name = customBundles.nextToken();
                try {
                    LOG.trace("Loading global messages from [{}]", name);
                    addDefaultResourceBundle(name);
                } catch (Exception e) {
                    LOG.error(new ParameterizedMessage("Could not find messages file {}.properties. Skipping", name), e);
                }
            }
        }
    }
