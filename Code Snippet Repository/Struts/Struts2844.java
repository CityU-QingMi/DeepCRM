    public static Class toClass(String type, ClassLoader loader)
            throws ClassNotFoundException {

        Class c = null;
        int i0 = type.indexOf('[');
        int dims = 0;
        if (i0 > 0) {
            // This is an array.  Count the dimensions
            for (int i = 0; i < type.length(); i++) {
                if (type.charAt(i) == '[')
                    dims++;
            }
            type = type.substring(0, i0);
        }

        if ("boolean".equals(type))
            c = boolean.class;
        else if ("char".equals(type))
            c = char.class;
        else if ("byte".equals(type))
            c = byte.class;
        else if ("short".equals(type))
            c = short.class;
        else if ("int".equals(type))
            c = int.class;
        else if ("long".equals(type))
            c = long.class;
        else if ("float".equals(type))
            c = float.class;
        else if ("double".equals(type))
            c = double.class;
        else if (type.indexOf('[') < 0)
            c = loader.loadClass(type);

        if (dims == 0)
            return c;

        if (dims == 1)
            return java.lang.reflect.Array.newInstance(c, 1).getClass();

        // Array of more than i dimension
        return java.lang.reflect.Array.newInstance(c, new int[dims]).getClass();
    }
