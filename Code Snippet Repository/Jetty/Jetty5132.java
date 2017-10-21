    private static boolean isBlank(String name)
    {
        if (name == null)
        {
            return true;
        }
        int size = name.length();
        char c;
        for (int i = 0; i < size; i++)
        {
            c = name.charAt(i);
            if (!Character.isWhitespace(c))
            {
                return false;
            }
        }
        return true;
    }
