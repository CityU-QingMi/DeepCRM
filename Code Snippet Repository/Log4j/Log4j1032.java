    @Override
    public boolean isValid(final String name, final Object value) {
        if (value == null) {
            LOGGER.error(annotation.message());
            return false;
        }
        if (value instanceof InetAddress) {
            // InetAddress factory methods all have built in validation
            return true;
        }
        try {
            InetAddress.getByName(value.toString());
            return true;
        } catch (final UnknownHostException e) {
            LOGGER.error(annotation.message(), e);
            return false;
        }
    }
