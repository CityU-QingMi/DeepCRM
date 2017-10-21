    public static Pattern compileWildcardPattern(String pattern) {
        StringBuilder buf = new StringBuilder(pattern);

        for (int i=buf.length()-1; i>=0; i--)
        {
            char c = buf.charAt(i);
            if (c == '*' && (i == 0 || buf.charAt(i-1) != '\\'))
            {
                buf.insert(i+1, '?');
                buf.insert(i, '.');
            }
            else if (c == '*')
            {
                i--;	// skip backslash, too
            }
            else if (needsBackslashToBeLiteralInRegex(c))
            {
                buf.insert(i, '\\');
            }
        }

        return Pattern.compile(buf.toString());
    }
