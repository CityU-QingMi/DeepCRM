    public static void initializePingVariants() {
        String configuredVal = WebloggerConfig.getProperty(PINGS_VARIANT_OPTIONS_PROP);
        if (configuredVal == null || configuredVal.trim().length() == 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No (or empty) value of " + PINGS_VARIANT_OPTIONS_PROP + " present in the configuration.  Skipping initialization of ping variants.");
            }
            return;
        }
        String[] variants = configuredVal.trim().split(",");
        for (int i = 0; i < variants.length; i++) {
            String thisVariant = variants[i].trim();
            if (thisVariant.length() == 0) {
                continue;
            }
            Matcher m = NESTED_BRACE_PAIR.matcher(thisVariant);
            if (m.matches() && m.groupCount() == 2) {
                String url = m.group(1).trim();
                String optionsList = m.group(2).trim();
                Set<String> variantOptions = new HashSet<String>();
                String[] options = optionsList.split(",");
                for (int j = 0; j < options.length; j++) {
                    String option = options[j].trim().toLowerCase();
                    if (option.length() > 0) {
                        variantOptions.add(option);
                    }
                }
                if (!variantOptions.isEmpty()) {
                    CONFIGURED_VARIANTS.put(url, variantOptions);
                } else {
                    LOGGER.warn("Ping variant entry for url '" + url + "' has an empty variant options list.  Ignored.");
                }
            } else {
                LOGGER.error("Unable to parse configured ping variant '" + thisVariant + "'. Skipping this variant. Check your setting of the property " + PINGS_VARIANT_OPTIONS_PROP);
            }
        }
    }
