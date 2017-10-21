    public static String unquoteOnly(String s, boolean lenient)
    {
        if (s==null)
            return null;
        if (s.length()<2)
            return s;

        char first=s.charAt(0);
        char last=s.charAt(s.length()-1);
        if (first!=last || (first!='"' && first!='\''))
            return s;

        StringBuilder b = new StringBuilder(s.length() - 2);
        boolean escape=false;
        for (int i=1;i<s.length()-1;i++)
        {
            char c = s.charAt(i);

            if (escape)
            {
                escape=false;
                if (lenient && !isValidEscaping(c))
                {
                    b.append('\\');
                }
                b.append(c);
            }
            else if (c=='\\')
            {
                escape=true;
            }
            else
            {
                b.append(c);
            }
        }

        return b.toString();
    }
