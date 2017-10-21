    private static <A extends Annotation> ConstraintValidator<A> getValidator(final A annotation,
                                                                              final Class<? extends A> type) {
        final Constraint constraint = type.getAnnotation(Constraint.class);
        final Class<? extends ConstraintValidator<?>> validatorClass = constraint.value();
        if (type.equals(getConstraintValidatorAnnotationType(validatorClass))) {
            @SuppressWarnings("unchecked") // I don't think we could be any more thorough in validation here
            final ConstraintValidator<A> validator = (ConstraintValidator<A>)
                ReflectionUtil.instantiate(validatorClass);
            validator.initialize(annotation);
            return validator;
        }
        return null;
    }
