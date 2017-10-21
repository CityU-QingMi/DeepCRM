    protected void setParameters(Map<String, Object> extraContext, Result result, Map<String, String> params) {
        for (Map.Entry<String, String> paramEntry : params.entrySet()) {
            try {
                String name = paramEntry.getKey();
                String value = paramEntry.getValue();
                setParameter(result, name, value, extraContext);
            } catch (ReflectionException ex) {
                if (result instanceof ReflectionExceptionHandler) {
                    ((ReflectionExceptionHandler) result).handle(ex);
                }
            }
        }
    }
