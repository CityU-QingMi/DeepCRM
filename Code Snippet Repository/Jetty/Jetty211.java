    @OnMessage
    public String onMessage(String msg)
    {
        StringWriter str = new StringWriter();
        PrintWriter out = new PrintWriter(str);
        
        String args[] = msg.split("\\|");

        switch (args[0])
        {
            case "info":
                out.printf("websocketSession is %s%n",asPresent(session));
                out.printf("httpSession is %s%n",asPresent(httpSession));
                out.printf("servletContext is %s%n",asPresent(servletContext));
                break;
            case "data":
                dataMaker.processMessage(args[1]);
                break;
        }

        return str.toString();
    }
