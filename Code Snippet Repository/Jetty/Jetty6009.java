    public boolean checkResult (String result, String[] outcomes)
    {
        boolean matched = false;
        for (String s:outcomes)
        {
            if (s.equals(result))
                matched = true;
        }
        return matched;
    }
