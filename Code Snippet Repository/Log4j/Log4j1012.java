    private void injectFields(final Builder<?> builder) throws IllegalAccessException {
        final List<Field> fields = TypeUtil.getAllDeclaredFields(builder.getClass());
        AccessibleObject.setAccessible(fields.toArray(new Field[] {}), true);
        final StringBuilder log = new StringBuilder();
        boolean invalid = false;
        for (final Field field : fields) {
            log.append(log.length() == 0 ? simpleName(builder) + "(" : ", ");
            final Annotation[] annotations = field.getDeclaredAnnotations();
            final String[] aliases = extractPluginAliases(annotations);
            for (final Annotation a : annotations) {
                if (a instanceof PluginAliases) {
                    continue; // already processed
                }
                final PluginVisitor<? extends Annotation> visitor =
                    PluginVisitors.findVisitor(a.annotationType());
                if (visitor != null) {
                    final Object value = visitor.setAliases(aliases)
                        .setAnnotation(a)
                        .setConversionType(field.getType())
                        .setStrSubstitutor(configuration.getStrSubstitutor())
                        .setMember(field)
                        .visit(configuration, node, event, log);
                    // don't overwrite default values if the visitor gives us no value to inject
                    if (value != null) {
                        field.set(builder, value);
                    }
                }
            }
            final Collection<ConstraintValidator<?>> validators =
                ConstraintValidators.findValidators(annotations);
            final Object value = field.get(builder);
            for (final ConstraintValidator<?> validator : validators) {
                if (!validator.isValid(field.getName(), value)) {
                    invalid = true;
                }
            }
        }
        log.append(log.length() == 0 ? builder.getClass().getSimpleName() + "()" : ")");
        LOGGER.debug(log.toString());
        if (invalid) {
            throw new ConfigurationException("Arguments given for element " + node.getName() + " are invalid");
        }
        checkForRemainingAttributes();
        verifyNodeChildrenUsed();
    }
