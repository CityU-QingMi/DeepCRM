    public static List<String> asLines(String raw) throws IOException
    {
        List<String> lines = new ArrayList<String>();
        StringReader sreader = null;
        BufferedReader buf = null;
        try
        {
            sreader = new StringReader(raw);
            buf = new BufferedReader(sreader);
            String line;
            while ((line = buf.readLine()) != null)
            {
                lines.add(line);
            }
        }
        finally
        {
            IO.close(buf);
            IO.close(sreader);
        }
        return lines;
    }
