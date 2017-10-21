        @SuppressWarnings("")
        @Override
        public KafkaAppender build() {
            final Layout<? extends Serializable> layout = getLayout();
            if (layout == null) {
                AbstractLifeCycle.LOGGER.error("No layout provided for KafkaAppender");
                return null;
            }
            final KafkaManager kafkaManager = new KafkaManager(getConfiguration().getLoggerContext(), getName(), topic, syncSend, properties);
            return new KafkaAppender(getName(), layout, getFilter(), isIgnoreExceptions(), kafkaManager);
        }
