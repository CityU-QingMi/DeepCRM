    @Override
    public String toString() {
        final StringBuilder msg = new StringBuilder();
        for (final Map.Entry<String, List<String>> entry : headers.entrySet()) {
            final String name = entry.getKey();
            final List<String> values = entry.getValue();
            for (final String value : values) {
                msg.append(name);
                msg.append(": ");
                msg.append(value);
                msg.append('\n');
            }
        }
        msg.append('\n');
        msg.append(body);
        msg.append('\n');
        return msg.toString();
    }
