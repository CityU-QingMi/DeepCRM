    protected void performOValValidation(Object action, ValueStack valueStack, String methodName, String context) throws NoSuchMethodException {
        Class clazz = action.getClass();
        //read validation from xmls
        List<Configurer> configurers = validationManager.getConfigurers(clazz, context, validateJPAAnnotations);

        Validator validator = configurers.isEmpty() ? new Validator() : new Validator(configurers);
        //if the method is annotated with a @Profiles annotation, use those profiles
        Method method = clazz.getMethod(methodName, new Class[0]);
        if (method != null) {
            Profiles profiles = method.getAnnotation(Profiles.class);
            if (profiles != null) {
                String[] profileNames = profiles.value();
                if (profileNames != null && profileNames.length > 0) {
                    validator.disableAllProfiles();
                    LOG.debug("Enabling profiles [{}]", StringUtils.join(profileNames, ","));
                    for (String profileName : profileNames)
                        validator.enableProfile(profileName);
                }
            }
        }

        //perform validation
        List<ConstraintViolation> violations = validator.validate(action);
        addValidationErrors(violations.toArray(new ConstraintViolation[violations.size()]), action, valueStack, null);
    }
