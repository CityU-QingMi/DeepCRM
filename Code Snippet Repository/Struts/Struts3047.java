    public static void handleSetPropertyExpression(Object bean,
                                                   String prop, String expression, PageContext pageContext,
                                                   ProtectedFunctionMapper functionMapper)
            throws JasperException {
        try {
            Method method = getWriteMethod(bean.getClass(), prop);
            method.invoke(bean, new Object[]{
                    PageContextImpl.proprietaryEvaluate(
                            expression,
                            method.getParameterTypes()[0],
                            pageContext,
                            functionMapper,
                            false)
            });
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
    }
