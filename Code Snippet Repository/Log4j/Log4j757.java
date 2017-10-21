    @Deprecated
    public static KafkaAppender createAppender(
            final Layout<? extends Serializable> layout,
            final Filter filter,
            final String name,
            final boolean ignoreExceptions,
            final String topic,
            final Property[] properties,
            final Configuration configuration) {

        if (layout == null) {
            AbstractLifeCycle.LOGGER.error("No layout provided for KafkaAppender");
            return null;
        }
        final KafkaManager kafkaManager = new KafkaManager(configuration.getLoggerContext(), name, topic, true, properties);
        return new KafkaAppender(name, layout, filter, ignoreExceptions, kafkaManager);
    }
