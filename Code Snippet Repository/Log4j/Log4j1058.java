    private static void enableXInclude(final DocumentBuilderFactory factory) {
        try {
            // Alternative: We set if a system property on the command line is set, for example:
            // -DLog4j.XInclude=true
            factory.setXIncludeAware(true);
        } catch (final UnsupportedOperationException e) {
            LOGGER.warn("The DocumentBuilderFactory [{}] does not support XInclude: {}", factory, e);
        } catch (@SuppressWarnings("ErrorNotRethrown") final AbstractMethodError | NoSuchMethodError err) {
            LOGGER.warn("The DocumentBuilderFactory [{}] is out of date and does not support XInclude: {}", factory,
                    err);
        }
        try {
            // Alternative: We could specify all features and values with system properties like:
            // -DLog4j.DocumentBuilderFactory.Feature="http://apache.org/xml/features/xinclude/fixup-base-uris true"
            factory.setFeature(XINCLUDE_FIXUP_BASE_URIS, true);
        } catch (final ParserConfigurationException e) {
            LOGGER.warn("The DocumentBuilderFactory [{}] does not support the feature [{}]: {}", factory,
                    XINCLUDE_FIXUP_BASE_URIS, e);
        } catch (@SuppressWarnings("ErrorNotRethrown") final AbstractMethodError err) {
            LOGGER.warn("The DocumentBuilderFactory [{}] is out of date and does not support setFeature: {}", factory,
                    err);
        }
        try {
            factory.setFeature(XINCLUDE_FIXUP_LANGUAGE, true);
        } catch (final ParserConfigurationException e) {
            LOGGER.warn("The DocumentBuilderFactory [{}] does not support the feature [{}]: {}", factory,
                    XINCLUDE_FIXUP_LANGUAGE, e);
        } catch (@SuppressWarnings("ErrorNotRethrown") final AbstractMethodError err) {
            LOGGER.warn("The DocumentBuilderFactory [{}] is out of date and does not support setFeature: {}", factory,
                    err);
        }
    }
