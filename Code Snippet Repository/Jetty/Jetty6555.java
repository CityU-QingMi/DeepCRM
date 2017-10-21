    protected void assertUnset(CallableMethod callable, Class<? extends Annotation> annoClass, Method method)
    {
        if (callable != null)
        {
            // Attempt to add duplicate frame type (a no-no)
            StringBuilder err = new StringBuilder();
            err.append("Duplicate @").append(annoClass.getSimpleName()).append(" declaration on ");
            err.append(method);
            err.append(System.lineSeparator());

            err.append("@").append(annoClass.getSimpleName()).append(" previously declared at ");
            err.append(callable.getMethod());

            throw new InvalidWebSocketException(err.toString());
        }
    }
