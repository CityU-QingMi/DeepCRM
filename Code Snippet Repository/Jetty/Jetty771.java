    private static CharSequence toId(Node node)
    {
        StringBuilder buf = new StringBuilder();

        for (char c : node.getName().toCharArray())
        {
            if (Character.isLetter(c))
            {
                buf.append(c);
                continue;
            }

            if (Character.isDigit(c))
            {
                buf.append(c);
                continue;
            }

            if ((c == ' ') || (c == '-') || (c == '_'))
            {
                buf.append(c);
                continue;
            }
        }

        return buf;
    }
