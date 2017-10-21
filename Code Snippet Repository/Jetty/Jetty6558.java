    protected boolean isSignatureMatch(Method method, ParamList validParams)
    {
        assertIsPublicNonStatic(method);
        assertIsReturn(method,Void.TYPE);

        // validate parameters
        Class<?> actual[] = method.getParameterTypes();
        for (Class<?>[] params : validParams)
        {
            if (isSameParameters(actual,params))
            {
                return true;
            }
        }

        return false;
    }
