    private static void invokeSetter(Object target, String attribute, Object value) throws Exception {
        String setterName = "set" + Character.toUpperCase(attribute.charAt(0)) + attribute.substring(1);
        Class<?>[] argTypes = {value.getClass()};
        Method setter = findMethod(target.getClass(), setterName, argTypes);
        if(setter != null) {
            setter.invoke(target, value);
        } else {
            throw new Exception("Unable to find setter for attribute '" + attribute
                    + "' and value '" + value + "'");
        }
    }
