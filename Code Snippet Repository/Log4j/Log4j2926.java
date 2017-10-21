    @Override
    public String lookup(final LogEvent event, final String key) {
        final Message msg = event.getMessage();
        if (msg instanceof StringMapMessage) {
            try {
                final Map<String, String> properties = ((StringMapMessage) msg).getData();
                if (properties == null) {
                    return "";
                }
                if (key == null || key.length() == 0 || key.equals("*")) {
                    final StringBuilder sb = new StringBuilder("{");
                    boolean first = true;
                    for (final Map.Entry<String, String> entry : properties.entrySet()) {
                        if (!first) {
                            sb.append(", ");
                        }
                        sb.append(entry.getKey()).append("=").append(entry.getValue());
                        first = false;
                    }
                    sb.append("}");
                    return sb.toString();
                }
                return properties.get(key);
            } catch (final Exception ex) {
                LOGGER.warn(LOOKUP, "Error while getting property [{}].", key, ex);
                return null;
            }
        }
        return null;
    }
