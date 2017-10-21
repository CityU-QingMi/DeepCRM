    protected void assertValidSignature(Method method, Class<? extends Annotation> annoClass, ParamList validParams)
    {
        assertIsPublicNonStatic(method);
        assertIsReturn(method,Void.TYPE);

        boolean valid = false;

        // validate parameters
        Class<?> actual[] = method.getParameterTypes();
        for (Class<?>[] params : validParams)
        {
            if (isSameParameters(actual,params))
            {
                valid = true;
                break;
            }
        }

        if (!valid)
        {
            throw InvalidSignatureException.build(method,annoClass,validParams);
        }
    }
