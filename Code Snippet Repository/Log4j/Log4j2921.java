        @Override
        public Object invoke(final Object o, final Method method, final Object[] objects)
            throws Throwable {
            if (method.getName().equals("logEvent")) {

                final StringBuilder missing = new StringBuilder();

                final Method[] methods = intrface.getMethods();

                for (final Method _method : methods) {
                    final String name = NamingUtils.lowerFirst(NamingUtils
                        .getMethodShortName(_method.getName()));

                    final Annotation[] annotations = _method.getDeclaredAnnotations();
                    for (final Annotation annotation : annotations) {
                        final Constraint constraint = (Constraint) annotation;

                        if (constraint.required() && msg.get(name) == null) {
                            if (missing.length() > 0) {
                                missing.append(", ");
                            }
                            missing.append(name);
                        }
                    }
                }

                if (missing.length() > 0) {
                    throw new IllegalStateException("Event " + msg.getId().getName() +
                        " is missing required attributes " + missing);
                }
                EventLogger.logEvent(msg);
            }
            if (method.getName().equals("setCompletionStatus")) {
                final String name = NamingUtils.lowerFirst(NamingUtils.getMethodShortName(method.getName()));
                msg.put(name, objects[0].toString());
            }
            if (method.getName().startsWith("set")) {
                final String name = NamingUtils.lowerFirst(NamingUtils.getMethodShortName(method.getName()));

/**/
/**/
/**/
/**/
                msg.put(name, objects[0].toString());
            }

            return null;
        }
