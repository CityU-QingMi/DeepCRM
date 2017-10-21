    public List<ValidatorConfig> buildAnnotationClassValidatorConfigs(Class aClass) {

        List<ValidatorConfig> result = new ArrayList<>();

        List<ValidatorConfig> temp = processAnnotations(aClass);
        if (temp != null) {
            result.addAll(temp);
        }

        Method[] methods = aClass.getDeclaredMethods();

        if (methods != null) {
            for (Method method : methods) {
                temp = processAnnotations(method);
                if (temp != null) {
                    result.addAll(temp);
                }
            }
        }

        return result;

    }
