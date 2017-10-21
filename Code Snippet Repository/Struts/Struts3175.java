    private boolean doesArgumentTypeMatchParamType(Param param, Object arg) {
        Class paramType = param.getType();
        Class<? extends Object> argClass = arg.getClass();

        // TODO(jpelly): Handle all primitive unwrapping (ie, Boolean --> boolean).
        if (boolean.class.equals(paramType) && Boolean.class.equals(argClass)) {
            return true;
        } else if (char.class.equals(paramType) && Character.class.equals(argClass)) {
            return true;
        }
        return paramType.isAssignableFrom(argClass);
    }
