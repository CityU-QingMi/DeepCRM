    private String readOutputLine(Process pidCmd) throws IOException
    {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader buf = null;
        try
        {
            in = pidCmd.getInputStream();
            reader = new InputStreamReader(in);
            buf = new BufferedReader(reader);
            return buf.readLine();
        }
        finally
        {
            IO.close(buf);
            IO.close(reader);
            IO.close(in);
        }
    }
