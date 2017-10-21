    public static Collection<ConstraintValidator<?>> findValidators(final Annotation... annotations) {
        final Collection<ConstraintValidator<?>> validators =
            new ArrayList<>();
        for (final Annotation annotation : annotations) {
            final Class<? extends Annotation> type = annotation.annotationType();
            if (type.isAnnotationPresent(Constraint.class)) {
                final ConstraintValidator<?> validator = getValidator(annotation, type);
                if (validator != null) {
                    validators.add(validator);
                }
            }
        }
        return validators;
    }
