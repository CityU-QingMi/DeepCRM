    private static int countChars(String pattern, int offset, char c)
    {
        int count = 0;
        int len = pattern.length();
        for (int i = offset; i < len; i++)
        {
            if (pattern.charAt(i) == c)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count;
    }
