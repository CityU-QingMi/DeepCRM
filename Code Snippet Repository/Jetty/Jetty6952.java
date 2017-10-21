    public static String toString(Throwable t)
    {
        try (StringWriter w = new StringWriter())
        {
            try (PrintWriter out = new PrintWriter(w))
            {
                t.printStackTrace(out);
                return w.toString();
            }
        }
        catch (IOException e)
        {
            return "Unable to get stacktrace for: " + t;
        }
    }
