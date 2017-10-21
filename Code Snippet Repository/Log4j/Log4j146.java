    private static Class<? extends MessageFactory> createClassForProperty(final String property,
            final Class<ReusableMessageFactory> reusableParameterizedMessageFactoryClass,
            final Class<ParameterizedMessageFactory> parameterizedMessageFactoryClass) {
        try {
            final String fallback = Constants.ENABLE_THREADLOCALS ? reusableParameterizedMessageFactoryClass.getName()
                    : parameterizedMessageFactoryClass.getName();
            final String clsName = PropertiesUtil.getProperties().getStringProperty(property, fallback);
            return LoaderUtil.loadClass(clsName).asSubclass(MessageFactory.class);
        } catch (final Throwable t) {
            return parameterizedMessageFactoryClass;
        }
    }
