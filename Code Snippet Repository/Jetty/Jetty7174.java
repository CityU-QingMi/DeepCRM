    public static void main(String... args)
    {
        try
        {
            int port = 8080;
            boolean verbose = false;
            String docroot = "src/test/webapp";

            for (int i = 0; i < args.length; i++)
            {
                String a = args[i];
                if ("-p".equals(a) || "--port".equals(a))
                {
                    port = Integer.parseInt(args[++i]);
                }
                else if ("-v".equals(a) || "--verbose".equals(a))
                {
                    verbose = true;
                }
                else if ("-d".equals(a) || "--docroot".equals(a))
                {
                    docroot = args[++i];
                }
                else if (a.startsWith("-"))
                {
                    usage();
                }
            }

            ExampleEchoServer server = new ExampleEchoServer(port);
            server.setVerbose(verbose);
            server.setResourceBase(docroot);
            server.runForever();
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
