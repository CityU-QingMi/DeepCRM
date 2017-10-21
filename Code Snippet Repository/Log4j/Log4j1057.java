    private static void setFeature(final DocumentBuilderFactory factory, final String featureName, final boolean value)
            throws ParserConfigurationException {
        try {
            factory.setFeature(featureName, value);
        } catch (ParserConfigurationException e) {
            throw e;
        } catch (Exception | LinkageError e) {
            getStatusLogger().error("Caught {} setting feature {} to {} on DocumentBuilderFactory {}: {}",
                    e.getClass().getCanonicalName(), featureName, value, factory, e, e);
        }
    }
