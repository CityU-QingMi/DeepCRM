    public static InvalidSignatureException build(Method method, Class<? extends Annotation> annoClass, ParamList... paramlists)
    {
        // Build big detailed exception to help the developer
        StringBuilder err = new StringBuilder();
        err.append("Invalid declaration of ");
        err.append(method);
        err.append(System.lineSeparator());

        err.append("Acceptable method declarations for @");
        err.append(annoClass.getSimpleName());
        err.append(" are:");
        for (ParamList validParams : paramlists)
        {
            for (Class<?>[] params : validParams)
            {
                err.append(System.lineSeparator());
                err.append("public void ").append(method.getName());
                err.append('(');
                boolean delim = false;
                for (Class<?> type : params)
                {
                    if (delim)
                    {
                        err.append(',');
                    }
                    err.append(' ');
                    err.append(type.getName());
                    if (type.isArray())
                    {
                        err.append("[]");
                    }
                    delim = true;
                }
                err.append(')');
            }
        }
        return new InvalidSignatureException(err.toString());
    }
