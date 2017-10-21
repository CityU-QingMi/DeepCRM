    protected String processInputConfig(final Object action, final String method, final String currentResultName) throws Exception {
        String resultName = currentResultName;
        InputConfig annotation = MethodUtils.getAnnotation(action.getClass().getMethod(method, EMPTY_CLASS_ARRAY),
                InputConfig.class ,true,true);
        if (annotation != null) {
            if (StringUtils.isNotEmpty(annotation.methodName())) {
                resultName = (String) MethodUtils.invokeMethod(action, true, annotation.methodName());
            } else {
                resultName = annotation.resultName();
            }
            LOG.debug("Changing result name from [{}] to [{}] because of processing annotation [{}] on action [{}]",
                        currentResultName, resultName, InputConfig.class.getSimpleName(), action);
        }
        return resultName;
    }
