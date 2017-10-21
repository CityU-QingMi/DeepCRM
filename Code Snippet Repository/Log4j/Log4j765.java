    @Override
    public LogEvent rewrite(final LogEvent source) {
        final Message msg = source.getMessage();
        if (msg == null || !(msg instanceof MapMessage)) {
            return source;
        }

        @SuppressWarnings("unchecked")
        final MapMessage<?, Object> mapMsg = (MapMessage<?, Object>) msg;
        final Map<String, Object> newMap = new HashMap<>(mapMsg.getData());
        switch (mode) {
            case Add: {
                newMap.putAll(map);
                break;
            }
            default: {
                for (final Map.Entry<String, Object> entry : map.entrySet()) {
                    if (newMap.containsKey(entry.getKey())) {
                        newMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        final Message message = mapMsg.newInstance(newMap);
        final LogEvent result = new Log4jLogEvent.Builder(source).setMessage(message).build();
        return result;
    }
