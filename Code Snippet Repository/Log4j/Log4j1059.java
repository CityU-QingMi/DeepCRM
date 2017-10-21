    @Override
    public void setup() {
        if (rootElement == null) {
            LOGGER.error("No logging configuration");
            return;
        }
        constructHierarchy(rootNode, rootElement);
        if (status.size() > 0) {
            for (final Status s : status) {
                LOGGER.error("Error processing element {} ({}): {}", s.name, s.element, s.errorType);
            }
            return;
        }
        rootElement = null;
    }
