    private void visitMethod(JsrCallable callable, Class<?> pojo, Method method, LinkedList<IJsrParamId> paramIds,
            Class<? extends Annotation> methodAnnotationClass)
    {
        // Identify all of the parameters
        for (Param param : callable.getParams())
        {
            if (!visitParam(callable,param,paramIds))
            {
                StringBuilder err = new StringBuilder();
                err.append("Encountered unknown parameter type <");
                err.append(param.type.getName());
                err.append("> on @");
                err.append(methodAnnotationClass.getSimpleName());
                err.append(" annotated method: ");
                err.append(ReflectUtils.toString(pojo,method));

                throw new InvalidSignatureException(err.toString());
            }
        }
    }
