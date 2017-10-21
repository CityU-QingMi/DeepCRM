    public static void main(String[] args)
        throws Exception
    {
        String jetty_home = System.getProperty("jetty.home",".");

        Server server = new Server(Integer.getInteger("jetty.http.port",8080).intValue());
                
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(jetty_home+"/target/async-rest/");
        webapp.setParentLoaderPriority(true);
        webapp.setServerClasses(new String[]{});
        server.setHandler(webapp);
        
        server.start();
        server.join();
    }
