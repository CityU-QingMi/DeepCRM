    protected void assertIsReturn(Method method, Class<?> type)
    {
        if (!type.equals(method.getReturnType()))
        {
            StringBuilder err = new StringBuilder();
            err.append("Invalid declaration of ");
            err.append(method);
            err.append(System.lineSeparator());

            err.append("Return type must be ").append(type);

            throw new InvalidWebSocketException(err.toString());
        }
    }
