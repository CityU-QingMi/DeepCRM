    public void execute() throws MojoExecutionException, MojoFailureException 
    {
        if (stopPort <= 0)
            throw new MojoExecutionException("Please specify a valid port"); 
        if (stopKey == null)
            throw new MojoExecutionException("Please specify a valid stopKey");  

        //Ensure jetty Server instance stops. Whether or not the remote process
        //also stops depends whether or not it was started with ShutdownMonitor.exitVm=true
        String command = "forcestop"; 
       
        try
        {        
            Socket s=new Socket(InetAddress.getByName("127.0.0.1"),stopPort);
            s.setSoLinger(false, 0);

            OutputStream out=s.getOutputStream();
            out.write((stopKey+"\r\n"+command+"\r\n").getBytes());
            out.flush();

            if (stopWait > 0)
            {      
                s.setSoTimeout(stopWait * 1000);
                s.getInputStream();

                getLog().info("Waiting "+stopWait+" seconds for jetty to stop");
                LineNumberReader lin = new LineNumberReader(new InputStreamReader(s.getInputStream()));
                String response;
                boolean stopped = false;
                while (!stopped && ((response = lin.readLine()) != null))
                {
                    if ("Stopped".equals(response))
                    {
                        stopped = true;
                        getLog().info("Server reports itself as stopped");
                    }                
                }
            }
            s.close();
        }
        catch (ConnectException e)
        {
            getLog().info("Jetty not running!");
        }
        catch (Exception e)
        {
            getLog().error(e);
        }
    }
