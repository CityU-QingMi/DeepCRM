    public List<Properties> getJarPoms() {
        try {
            return configHelper.getJarProperties();
        } catch (IOException ioe) {
            // this is the config browser, so it doesn't seem necessary to do more than just
            // send up a debug message
            if (LOG.isDebugEnabled()) {
                LOG.debug("IOException caught while retrieving jar properties - " + ioe.getMessage());
            }
            return Collections.emptyList(); // maybe avoiding NPE later
        }
    }
