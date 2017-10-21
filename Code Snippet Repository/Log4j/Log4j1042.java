    public static PluginVisitor<? extends Annotation> findVisitor(final Class<? extends Annotation> annotation) {
        final PluginVisitorStrategy strategy = annotation.getAnnotation(PluginVisitorStrategy.class);
        if (strategy == null) {
            return null;
        }
        try {
            return strategy.value().newInstance();
        } catch (final Exception e) {
            LOGGER.error("Error loading PluginVisitor [{}] for annotation [{}].", strategy.value(), annotation, e);
            return null;
        }
    }
