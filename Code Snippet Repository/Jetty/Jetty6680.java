    public static String toShortName(Type type)
    {
        if (type == null)
        {
            return "<null>";
        }

        if (type instanceof Class)
        {
            String name = ((Class<?>)type).getName();
            return trimClassName(name);
        }

        if (type instanceof ParameterizedType)
        {
            ParameterizedType ptype = (ParameterizedType)type;
            StringBuilder str = new StringBuilder();
            str.append(trimClassName(((Class<?>)ptype.getRawType()).getName()));
            str.append("<");
            Type args[] = ptype.getActualTypeArguments();
            for (int i = 0; i < args.length; i++)
            {
                if (i > 0)
                {
                    str.append(",");
                }
                str.append(args[i]);
            }
            str.append(">");
            return str.toString();
        }

        return type.toString();
    }
