    public static String quote(String arg)
    {
        boolean needsQuoting = (arg.indexOf(' ') >= 0) || (arg.indexOf('"') >= 0);
        if (!needsQuoting)
        {
            return arg;
        }
        StringBuilder buf = new StringBuilder();
        // buf.append('"');
        boolean escaped = false;
        boolean quoted = false;
        for (char c : arg.toCharArray())
        {
            if (!quoted && !escaped && ((c == '"') || (c == ' ')))
            {
                buf.append("\\");
            }
            // don't quote text in single quotes
            if (!escaped && (c == '\''))
            {
                quoted = !quoted;
            }
            escaped = (c == '\\');
            buf.append(c);
        }
        // buf.append('"');
        return buf.toString();
    }
