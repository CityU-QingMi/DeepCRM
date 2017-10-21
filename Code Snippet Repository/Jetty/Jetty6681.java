    public static String toString(Class<?> pojo, Method method)
    {
        StringBuilder str = new StringBuilder();

        // method modifiers
        int mod = method.getModifiers() & Modifier.methodModifiers();
        if (mod != 0)
        {
            str.append(Modifier.toString(mod)).append(' ');
        }

        // return type
        Type retType = method.getGenericReturnType();
        appendTypeName(str,retType,false).append(' ');

        // class name
        str.append(pojo.getName());
        str.append("#");

        // method name
        str.append(method.getName());

        // method parameters
        str.append('(');
        Type[] params = method.getGenericParameterTypes();
        for (int j = 0; j < params.length; j++)
        {
            boolean ellipses = method.isVarArgs() && (j == (params.length - 1));
            appendTypeName(str,params[j],ellipses);
            if (j < (params.length - 1))
            {
                str.append(", ");
            }
        }
        str.append(')');

        // TODO: show exceptions?
        return str.toString();
    }
