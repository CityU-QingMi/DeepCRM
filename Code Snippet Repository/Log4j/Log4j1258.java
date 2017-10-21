    @PluginFactory
    public static Rfc5424Layout createLayout(
            // @formatter:off
            @PluginAttribute(value = "facility", defaultString = "LOCAL0") final Facility facility,
            @PluginAttribute("id") final String id,
            @PluginAttribute(value = "enterpriseNumber", defaultInt = DEFAULT_ENTERPRISE_NUMBER)
            final int enterpriseNumber,
            @PluginAttribute(value = "includeMDC", defaultBoolean = true) final boolean includeMDC,
            @PluginAttribute(value = "mdcId", defaultString = DEFAULT_MDCID) final String mdcId,
            @PluginAttribute("mdcPrefix") final String mdcPrefix,
            @PluginAttribute("eventPrefix") final String eventPrefix,
            @PluginAttribute(value = "newLine") final boolean newLine,
            @PluginAttribute("newLineEscape") final String escapeNL,
            @PluginAttribute("appName") final String appName,
            @PluginAttribute("messageId") final String msgId,
            @PluginAttribute("mdcExcludes") final String excludes,
            @PluginAttribute("mdcIncludes") String includes,
            @PluginAttribute("mdcRequired") final String required,
            @PluginAttribute("exceptionPattern") final String exceptionPattern,
            // RFC 5425
            @PluginAttribute(value = "useTlsMessageFormat") final boolean useTlsMessageFormat,
            @PluginElement("LoggerFields") final LoggerFields[] loggerFields,
            @PluginConfiguration final Configuration config) {
        // @formatter:on
        if (includes != null && excludes != null) {
            LOGGER.error("mdcIncludes and mdcExcludes are mutually exclusive. Includes wil be ignored");
            includes = null;
        }

        return new Rfc5424Layout(config, facility, id, enterpriseNumber, includeMDC, newLine, escapeNL, mdcId,
                mdcPrefix, eventPrefix, appName, msgId, excludes, includes, required, StandardCharsets.UTF_8,
                exceptionPattern, useTlsMessageFormat, loggerFields);
    }
