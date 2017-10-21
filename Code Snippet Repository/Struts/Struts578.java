    private void findFieldValidators(String name, Class actionClass, String actionName,
                                     List<Validator> validatorList, List<Validator> resultValidators, String prefix) {

        for (Validator validator : validatorList) {
            if (validator instanceof FieldValidator) {
                FieldValidator fieldValidator = (FieldValidator) validator;

                if (validator instanceof VisitorFieldValidator) {
                    VisitorFieldValidator vfValidator = (VisitorFieldValidator) fieldValidator;
                    Class clazz = getVisitorReturnType(actionClass, vfValidator.getFieldName());
                    if (clazz == null) {
                        continue;
                    }

                    List<Validator> visitorValidators = actionValidatorManager.getValidators(clazz, actionName);
                    String vPrefix = prefix + (vfValidator.isAppendPrefix() ? vfValidator.getFieldName() + "." : "");
                    findFieldValidators(name, clazz, actionName, visitorValidators, resultValidators, vPrefix);
                } else if ((prefix + fieldValidator.getFieldName()).equals(name)) {
                    if (StringUtils.isNotBlank(prefix)) {
                        //fixing field name for js side
                        FieldVisitorValidatorWrapper wrap = new FieldVisitorValidatorWrapper(fieldValidator, prefix);
                        resultValidators.add(wrap);
                    } else {
                        resultValidators.add(fieldValidator);
                    }
                }
            }
        }
    }
