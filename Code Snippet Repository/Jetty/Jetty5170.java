    private void escape(StringBuilder builder, String string)
    {
        if (__escape)
        {
            for (int i = 0; i < string.length(); ++i)
            {
                char c = string.charAt(i);
                if (Character.isISOControl(c))
                {
                    if (c == '\n')
                    {
                        builder.append('|');
                    }
                    else if (c == '\r')
                    {
                        builder.append('<');
                    }
                    else
                    {
                        builder.append('?');
                    }
                }
                else
                {
                    builder.append(c);
                }
            }
        }
        else
            builder.append(string);
    }
