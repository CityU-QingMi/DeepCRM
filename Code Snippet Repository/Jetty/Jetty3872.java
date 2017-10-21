    private ServletHolder getHolder(ServletHolder[] holders, String servlet)
    {
        if (holders == null)
            return null;

        ServletHolder holder = null;
        for (int i=0; holder==null && i<holders.length; i++)
        {
            if (holders[i].getName().equals(servlet))
            {
                holder = holders[i];
            }
        }
        return holder;
    }
