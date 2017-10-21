    public static JmsMessageFactory getMessageFactory(final String name)
            throws JmsJobException {

        try {
            final Class<?> cls = Class.forName(name);

            final JmsMessageFactory factory = (JmsMessageFactory) cls
                    .newInstance();

            return factory;
        } catch (final Exception e) {
            throw new JmsJobException(e.getMessage(), e);
        }

    }
