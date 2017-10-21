    public final static void throwUnhandled(Object base, Object property)
            throws ELException {
        if (base == null) {
            throw new PropertyNotFoundException(MessageFactory.get(
                    "error.resolver.unhandled.null", property));
        } else {
            throw new PropertyNotFoundException(MessageFactory.get(
                    "error.resolver.unhandled", base.getClass(), property));
        }
    }
