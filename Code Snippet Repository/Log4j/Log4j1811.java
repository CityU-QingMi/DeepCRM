    private Map<String, Object> createBindings() {
        final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put(CONNECTION_FACTORY_NAME, connectionFactory);
        map.put(DESTINATION_NAME, destination);
        map.put(QUEUE_FACTORY_NAME, connectionFactory);
        map.put(QUEUE_NAME, destination);
        map.put(TOPIC_FACTORY_NAME, connectionFactory);
        map.put(TOPIC_NAME, destination);
        return map;
    }
