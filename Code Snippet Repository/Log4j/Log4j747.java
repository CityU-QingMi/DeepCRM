    private MapMessage map(final org.apache.logging.log4j.message.MapMessage<?, ?> log4jMapMessage,
            final MapMessage jmsMapMessage) {
        // Map without calling rg.apache.logging.log4j.message.MapMessage#getData() which makes a copy of the map.
        log4jMapMessage.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(final String key, final Object value) {
                try {
                    jmsMapMessage.setObject(key, value);
                } catch (final JMSException e) {
                    throw new IllegalArgumentException(String.format("%s mapping key '%s' to value '%s': %s",
                            e.getClass(), key, value, e.getLocalizedMessage()), e);
                }
            }
        });
        return jmsMapMessage;
    }
