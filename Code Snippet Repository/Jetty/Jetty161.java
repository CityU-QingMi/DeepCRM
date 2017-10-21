    public void execute() throws BuildException
    {
        try
        {        
            Socket s = new Socket(InetAddress.getByName("127.0.0.1"),stopPort);
            if (stopWait > 0)
                s.setSoTimeout(stopWait*1000);
            try
            {
                OutputStream out = s.getOutputStream();
                out.write((stopKey + "\r\nstop\r\n").getBytes());
                out.flush();

                if (stopWait > 0)
                {
                    TaskLog.log("Waiting"+(stopWait > 0 ? (" "+stopWait+"sec") : "")+" for jetty to stop");
                    LineNumberReader lin = new LineNumberReader(new InputStreamReader(s.getInputStream()));
                    String response=lin.readLine();
                    if ("Stopped".equals(response))
                        System.err.println("Stopped");
                }
            }
            finally
            {
                s.close();
            }  
        }
        catch (ConnectException e)
        {
            TaskLog.log("Jetty not running!");
        }
        catch (Exception e)
        {
            TaskLog.log(e.getMessage());
        }
    }
