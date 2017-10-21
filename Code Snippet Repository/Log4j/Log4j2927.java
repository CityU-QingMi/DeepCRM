    public Message convertEvent(final String message, final Object[] objects, final Throwable throwable) {
        try {
            final EventData data = objects != null && objects[0] instanceof EventData ?
                    (EventData) objects[0] : new EventData(message);
            final StructuredDataMessage msg =
                    new StructuredDataMessage(data.getEventId(), data.getMessage(), data.getEventType());
            for (final Map.Entry<String, Object> entry : data.getEventMap().entrySet()) {
                final String key = entry.getKey();
                if (EventData.EVENT_TYPE.equals(key) || EventData.EVENT_ID.equals(key)
                        || EventData.EVENT_MESSAGE.equals(key)) {
                    continue;
                }
                msg.put(key, String.valueOf(entry.getValue()));
            }
            return msg;
        } catch (final Exception ex) {
            return new ParameterizedMessage(message, objects, throwable);
        }
    }
