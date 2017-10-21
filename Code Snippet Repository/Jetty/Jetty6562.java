    public String formatMethodCallError(Object... args)
    {
        StringBuilder err = new StringBuilder();
        err.append("Cannot call method ");
        err.append(ReflectUtils.toString(pojo,method));
        err.append(" with args: [");

        boolean delim = false;
        for (Object arg : args)
        {
            if (delim)
            {
                err.append(", ");
            }
            if (arg == null)
            {
                err.append("<null>");
            }
            else
            {
                err.append(arg.getClass().getName());
            }
            delim = true;
        }
        err.append("]");
        return err.toString();
    }
