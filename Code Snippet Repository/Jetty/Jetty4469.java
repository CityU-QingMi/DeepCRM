    @Override
    public String toString()
    {
        StringBuffer cp = new StringBuffer(1024);
        boolean needDelim = false;
        for (File element : elements)
        {
            if (needDelim)
            {
                cp.append(File.pathSeparatorChar);
            }
            cp.append(element.getAbsolutePath());
            needDelim = true;
        }
        return cp.toString();
    }
