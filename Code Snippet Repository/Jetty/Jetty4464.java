    public boolean addClasspath(String s)
    {
        boolean added = false;
        if (s != null)
        {
            StringTokenizer t = new StringTokenizer(s,File.pathSeparator);
            while (t.hasMoreTokens())
            {
                added |= addComponent(t.nextToken());
            }
        }
        return added;
    }
