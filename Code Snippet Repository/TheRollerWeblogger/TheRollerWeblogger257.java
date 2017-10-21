    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("USAGE: java -cp roller-planet.jar TaskRunner WEBAPPDIR JARSDIR CLASSNAME");
            System.err.println("WEBAPPDIR: The directory path to the web application ");
            System.err.println("           (e.g. $CATALINA_HOME/webapps/roller)");
            System.err.println("JARSDIR:   The directory path to the additional jars ");
            System.err.println("           directory (e.g. $CATALINA_HOME/common/lib)");
            System.err.println("CLASSNAME: The name of the class to be executed by TaskRunner ");
            System.exit(-1);
        }
        String webappDir     = args[0];
        String jarsDir       = args[1];
        String taskClassName = args[2];
        System.out.println("WEBAPPDIR = " + webappDir); 
        System.out.println("JARSDIR   = " + jarsDir);
        System.out.println("CLASSNAME = " + taskClassName);
        
        File webappDirFile = new File(webappDir);
        File jarsDirFile = new File(jarsDir);
        if (!webappDirFile.isDirectory() || !jarsDirFile.isDirectory()) {
            System.err.println("ERROR: webapp.dir and jars.dir must specify existing directories");
            System.exit(-1);
        }        
        
        ClassLoader cl = new StandaloneWebappClassLoader(webappDir, jarsDir, null);
       
        // We're using the new classloader from here on out
        Thread.currentThread().setContextClassLoader(cl);

        // Go!
        Class taskClass = cl.loadClass(taskClassName);
        Runnable task = (Runnable)taskClass.newInstance();
        task.run();
    }
