    private void doStop(StartArgs args)
    {
        Props.Prop stopHostProp = args.getProperties().getProp("STOP.HOST", true);
        Props.Prop stopPortProp = args.getProperties().getProp("STOP.PORT", true);
        Props.Prop stopKeyProp = args.getProperties().getProp("STOP.KEY", true);
        Props.Prop stopWaitProp = args.getProperties().getProp("STOP.WAIT", true);
        
        String stopHost = "127.0.0.1";
        int stopPort = -1;
        String stopKey = "";
    
        if (stopHostProp != null)
        {
            stopHost = stopHostProp.value;
        }
    
        if (stopPortProp != null)
        {
            stopPort = Integer.parseInt(stopPortProp.value);
        }
        
        if(stopKeyProp != null)
        {
            stopKey = stopKeyProp.value;
        }

        if (stopWaitProp != null)
        {
            int stopWait = Integer.parseInt(stopWaitProp.value);
            stop(stopHost, stopPort, stopKey, stopWait);
        }
        else
        {
            stop(stopHost, stopPort, stopKey);
        }
    }
