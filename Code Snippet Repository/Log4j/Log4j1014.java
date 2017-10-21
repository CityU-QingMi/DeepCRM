    private Object[] generateParameters(final Method factory) {
        final StringBuilder log = new StringBuilder();
        final Class<?>[] types = factory.getParameterTypes();
        final Annotation[][] annotations = factory.getParameterAnnotations();
        final Object[] args = new Object[annotations.length];
        boolean invalid = false;
        for (int i = 0; i < annotations.length; i++) {
            log.append(log.length() == 0 ? factory.getName() + "(" : ", ");
            final String[] aliases = extractPluginAliases(annotations[i]);
            for (final Annotation a : annotations[i]) {
                if (a instanceof PluginAliases) {
                    continue; // already processed
                }
                final PluginVisitor<? extends Annotation> visitor = PluginVisitors.findVisitor(
                    a.annotationType());
                if (visitor != null) {
                    final Object value = visitor.setAliases(aliases)
                        .setAnnotation(a)
                        .setConversionType(types[i])
                        .setStrSubstitutor(configuration.getStrSubstitutor())
                        .setMember(factory)
                        .visit(configuration, node, event, log);
                    // don't overwrite existing values if the visitor gives us no value to inject
                    if (value != null) {
                        args[i] = value;
                    }
                }
            }
            final Collection<ConstraintValidator<?>> validators =
                ConstraintValidators.findValidators(annotations[i]);
            final Object value = args[i];
            final String argName = "arg[" + i + "](" + simpleName(value) + ")";
            for (final ConstraintValidator<?> validator : validators) {
                if (!validator.isValid(argName, value)) {
                    invalid = true;
                }
            }
        }
        log.append(log.length() == 0 ? factory.getName() + "()" : ")");
        checkForRemainingAttributes();
        verifyNodeChildrenUsed();
        LOGGER.debug(log.toString());
        if (invalid) {
            throw new ConfigurationException("Arguments given for element " + node.getName() + " are invalid");
        }
        return args;
    }
