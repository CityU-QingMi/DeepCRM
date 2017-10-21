    public List getValidators(String name) {
        Class actionClass = (Class) getParameters().get("actionClass");
        if (actionClass == null) {
            return Collections.EMPTY_LIST;
        }

        String formActionValue = findString(action);
        ActionMapping mapping = actionMapper.getMappingFromActionName(formActionValue);

        if (mapping == null) {
            mapping =  actionMapper.getMappingFromActionName((String) getParameters().get("actionName"));
        }

        if (mapping == null) {
            return Collections.EMPTY_LIST;
        }

        String actionName = mapping.getName();

        String methodName = null;
        if (isValidateAnnotatedMethodOnly(actionName)) {
            methodName = mapping.getMethod();
        }
        
        List<Validator> actionValidators = actionValidatorManager.getValidators(actionClass, actionName, methodName);
        List<Validator> validators = new ArrayList<>();

        findFieldValidators(name, actionClass, actionName, actionValidators, validators, "");

        return validators;
    }
