    private boolean checkSMDMethodSignature(Method method, String name, int parameterCount) {

        SMDMethod smdMethodAnntotation = method.getAnnotation(SMDMethod.class);
        if (smdMethodAnntotation != null) {
            String alias = smdMethodAnntotation.name();
            boolean paramsMatch = method.getParameterTypes().length == parameterCount;
            if (((alias.length() == 0) && method.getName().equals(name) && paramsMatch)
                    || (alias.equals(name) && paramsMatch)) {
                return true;
            }
        }

        return false;
    }
