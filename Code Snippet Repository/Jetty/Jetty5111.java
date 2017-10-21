    public static void dumpObject(Appendable out, Object o) throws IOException
    {
        try
        {
            if (o instanceof LifeCycle)
                out.append(String.valueOf(o)).append(" - ").append((AbstractLifeCycle.getState((LifeCycle)o))).append("\n");
            else
                out.append(String.valueOf(o)).append("\n");
        }
        catch (Throwable th)
        {
            out.append(" => ").append(th.toString()).append('\n');
        }
    }
