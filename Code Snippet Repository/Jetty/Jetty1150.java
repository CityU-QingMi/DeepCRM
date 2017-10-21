    private static int octetsNeeded(final int[][] table,String s)
    {   
        int needed=0;
        int len = s.length();
        for (int i=0;i<len;i++)
        {
            char c=s.charAt(i);
            if (c>=128 || c<' ')
                throw new IllegalArgumentException();
            needed += table[c][1];
        }

        return (needed+7) / 8;
    }
