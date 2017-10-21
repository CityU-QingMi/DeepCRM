    @Test
    public void testFlush() throws Exception
    {
        configureServer(new DataHandler());

        String[] encoding = {"NONE", "UTF-8", "ISO-8859-1", "ISO-8859-2"};
        for (int e = 0; e < encoding.length; e++)
        {
            for (int b = 1; b <= 128; b = b == 1 ? 2 : b == 2 ? 32 : b == 32 ? 128 : 129)
            {
                for (int w = 41; w < 42; w += 4096)
                {
                    for (int c = 0; c < 1; c++)
                    {
                        String test = encoding[e] + "x" + b + "x" + w + "x" + c;
                        try
                        {
                            URL url = new URL(_scheme + "://" + _serverURI.getHost() + ":" + _serverURI.getPort() + "/?writes=" + w + "&block=" + b + (e == 0 ? "" : ("&encoding=" + encoding[e])) + (c == 0 ? "&chars=true" : ""));

                            InputStream in = (InputStream)url.getContent();
                            String response = IO.toString(in, e == 0 ? null : encoding[e]);

                            assertEquals(test, b * w, response.length());
                        }
                        catch (Exception x)
                        {
                            System.err.println(test);
                            x.printStackTrace();
                            throw x;
                        }
                    }
                }
            }
        }
    }
