    private static Method findMethod(Class<?> targetType, String methodName,
            Class<?>[] argTypes) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(targetType);
        if (beanInfo != null) {
            for(MethodDescriptor methodDesc: beanInfo.getMethodDescriptors()) {
                Method method = methodDesc.getMethod();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (methodName.equals(method.getName()) && argTypes.length == parameterTypes.length) {
                    boolean matchedArgTypes = true;
                    for (int i = 0; i < argTypes.length; i++) { 
                        if (getWrapperIfPrimitive(argTypes[i]) != parameterTypes[i]) {
                            matchedArgTypes = false;
                            break;
                        }
                    }
                    if (matchedArgTypes) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
