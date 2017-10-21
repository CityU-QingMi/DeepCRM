    public List<Validator> getValidators(Class clazz, String context, String method) {
        final String validatorKey = buildValidatorKey(clazz, context);
        final List<ValidatorConfig> cfgs;

        if (validatorCache.containsKey(validatorKey)) {
            if (reloadingConfigs) {
                validatorCache.put(validatorKey, buildValidatorConfigs(clazz, context, true, null));
            }
        } else {
            validatorCache.put(validatorKey, buildValidatorConfigs(clazz, context, false, null));
        }

        // get the set of validator configs
        cfgs = new ArrayList<ValidatorConfig>(validatorCache.get(validatorKey));

        ValueStack stack = ActionContext.getContext().getValueStack();

        // create clean instances of the validators for the caller's use
        ArrayList<Validator> validators = new ArrayList<>(cfgs.size());
        for (ValidatorConfig cfg : cfgs) {
            if (method == null || method.equals(cfg.getParams().get("methodName"))) {
                Validator validator = validatorFactory.getValidator(
                        new ValidatorConfig.Builder(cfg)
                                .removeParam("methodName")
                                .build());
                validator.setValidatorType(cfg.getType());
                validator.setValueStack(stack);
                validators.add(validator);
            }
        }

        return validators;
    }
