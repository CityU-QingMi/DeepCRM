    private static void writeProcessInput(final Process p, final InputStream input, final int len)
    {
        if (len <= 0) return;

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    OutputStream outToCgi = p.getOutputStream();
                    IO.copy(input, outToCgi, len);
                    outToCgi.close();
                }
                catch (IOException e)
                {
                    LOG.debug(e);
                }
            }
        }).start();
    }
