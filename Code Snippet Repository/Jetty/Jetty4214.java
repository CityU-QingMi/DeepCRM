    private static void writeProcessInput(final Process p, final String input)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    try (Writer outToCgi = new OutputStreamWriter(p.getOutputStream()))
                    {
                        outToCgi.write(input);
                    }
                }
                catch (IOException e)
                {
                    LOG.debug(e);
                }
            }
        }).start();
    }
