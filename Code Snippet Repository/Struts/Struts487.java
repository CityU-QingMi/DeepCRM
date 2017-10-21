    protected List<ValidatorConfig> buildClassValidatorConfigs(Class aClass, boolean checkFile) {

        String fileName = aClass.getName().replace('.', '/') + VALIDATION_CONFIG_SUFFIX;

        List<ValidatorConfig> result = new ArrayList<>(loadFile(fileName, aClass, checkFile));

        AnnotationValidationConfigurationBuilder builder = new AnnotationValidationConfigurationBuilder(validatorFactory);

        List<ValidatorConfig> annotationResult = new ArrayList<>(builder.buildAnnotationClassValidatorConfigs(aClass));

        result.addAll(annotationResult);

        return result;

    }
