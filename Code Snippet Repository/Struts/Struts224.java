    public Result buildResult(ResultConfig resultConfig, Map<String, Object> extraContext) throws Exception {
        String resultClassName = resultConfig.getClassName();
        Result result = null;

        if (resultClassName != null) {
            result = (Result) objectFactory.buildBean(resultClassName, extraContext);
            Map<String, String> params = resultConfig.getParams();
            if (params != null) {
                for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                    try {
                        reflectionProvider.setProperty(paramEntry.getKey(), paramEntry.getValue(), result, extraContext, true);
                    } catch (ReflectionException ex) {
                        if (result instanceof ReflectionExceptionHandler) {
                            ((ReflectionExceptionHandler) result).handle(ex);
                        }
                    }
                }
            }
        }

        return result;
    }
