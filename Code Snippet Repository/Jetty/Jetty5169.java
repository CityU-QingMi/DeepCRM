    private void format(StringBuilder builder, String msg, Object... args)
    {
        if (msg == null)
        {
            msg = "";
            for (int i = 0; i < args.length; i++)
            {
                msg += "{} ";
            }
        }
        String braces = "{}";
        int start = 0;
        for (Object arg : args)
        {
            int bracesIndex = msg.indexOf(braces,start);
            if (bracesIndex < 0)
            {
                escape(builder,msg.substring(start));
                builder.append(" ");
                builder.append(arg);
                start = msg.length();
            }
            else
            {
                escape(builder,msg.substring(start,bracesIndex));
                builder.append(String.valueOf(arg));
                start = bracesIndex + braces.length();
            }
        }
        escape(builder,msg.substring(start));
    }
