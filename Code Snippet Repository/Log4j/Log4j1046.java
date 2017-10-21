    private AppenderRefComponentBuilder createAppenderRef(final String key, final Properties properties) {
        final String ref = (String) properties.remove("ref");
        if (Strings.isEmpty(ref)) {
            throw new ConfigurationException("No ref attribute provided for AppenderRef " + key);
        }
        final AppenderRefComponentBuilder appenderRefBuilder = builder.newAppenderRef(ref);
        final String level = Strings.trimToNull((String) properties.remove("level"));
        if (!Strings.isEmpty(level)) {
            appenderRefBuilder.addAttribute("level", level);
        }
        return addFiltersToComponent(appenderRefBuilder, properties);
    }
