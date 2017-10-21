    private void assertNotDuplicate(JsrCallable callable, Class<? extends Annotation> methodAnnotationClass, Class<?> pojo, Method method)
    {
        if (callable != null)
        {
            // Duplicate annotation detected
            StringBuilder err = new StringBuilder();
            err.append("Encountered duplicate method annotations @");
            err.append(methodAnnotationClass.getSimpleName());
            err.append(" on ");
            err.append(ReflectUtils.toString(pojo,callable.getMethod()));
            err.append(" and ");
            err.append(ReflectUtils.toString(pojo,method));

            throw new InvalidSignatureException(err.toString());
        }
    }
