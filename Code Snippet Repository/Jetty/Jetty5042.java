    public static int indexOfControlChars(String str)
    {
        if (str == null)
        {
            return -1;
        }
        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            if (Character.isISOControl(str.codePointAt(i)))
            {
                // found a control character, we can stop searching  now
                return i;
            }
        }
        // no control characters
        return -1;
    }
