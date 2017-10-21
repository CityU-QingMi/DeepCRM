    public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {
        CompoundRoot root = (CompoundRoot) target;
        OgnlContext ognlContext = (OgnlContext) context;

        for (Object o : root) {
            if (o == null) {
                continue;
            }

            try {
                if (OgnlRuntime.hasSetProperty(ognlContext, o, name)) {
                    OgnlRuntime.setProperty(ognlContext, o, name, value);

                    return;
                } else if (o instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<Object, Object> map = (Map<Object, Object>) o;
                    try {
                        map.put(name, value);
                        return;
                    } catch (UnsupportedOperationException e) {
                        // This is an unmodifiable Map, so move on to the next element in the stack
                    }
                }
//            } catch (OgnlException e) {
//                if (e.getReason() != null) {
//                    final String msg = "Caught an Ognl exception while setting property " + name;
//                    log.error(msg, e);
//                    throw new RuntimeException(msg, e.getReason());
//                }
            } catch (IntrospectionException e) {
                // this is OK if this happens, we'll just keep trying the next
            }
        }

        boolean reportError = toBoolean((Boolean) context.get(ValueStack.REPORT_ERRORS_ON_NO_PROP));

        if (reportError || devMode) {
            final String msg = format("No object in the CompoundRoot has a publicly accessible property named '%s' " +
                    "(no setter could be found).", name);
            if (reportError) {
                throw new XWorkException(msg);
            } else {
                LOG.warn(msg);
            }
        }
    }
