    public static void main(String[] args)
    {
        int port = 8080;

        for (int i = 0; i < args.length; i++)
        {
            String a = args[i];
            if ("-p".equals(a) || "--port".equals(a))
            {
                port = Integer.parseInt(args[++i]);
            }
        }

        try
        {
            JsrBrowserDebugTool tool = new JsrBrowserDebugTool();
            tool.setupServer(port);
            tool.server.start();
            tool.server.dumpStdErr();
            LOG.info("Server available at {}", tool.server.getURI());
            tool.server.join();
        }
        catch (Throwable t)
        {
            LOG.warn(t);
        }
    }
