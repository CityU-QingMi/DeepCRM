    @Override
    protected void after() {
        if (framework != null) {
            try {
                framework.stop();
            } catch (final BundleException e) {
                throw new RuntimeException(e);
            } finally {
                framework = null;
            }
        }
    }
