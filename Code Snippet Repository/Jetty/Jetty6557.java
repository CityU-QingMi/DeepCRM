    public boolean isSameParameters(Class<?>[] actual, Class<?>[] params)
    {
        if (actual.length != params.length)
        {
            // skip
            return false;
        }

        int len = params.length;
        for (int i = 0; i < len; i++)
        {
            if (!actual[i].equals(params[i]))
            {
                return false; // not valid
            }
        }

        return true;
    }
