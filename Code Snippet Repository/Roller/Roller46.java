    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("USAGE: java -cp roller-business.jar TaskRunner WEBAPPDIR JARSDIR SCRIPTNAME");
            System.err.println("WEBAPPDIR:  The directory path to the web application ");
            System.err.println("            (e.g. $CATALINA_HOME/webapps/roller)");
            System.err.println("JARSDIR:    The directory path to the additional jars ");
            System.err.println("            directory (e.g. $CATALINA_HOME/common/lib)");
            System.err.println("SCRIPTNAME: The name of the class to be executed by TaskRunner ");
            System.exit(-1);
        }
        String webappDir  = args[0];
        String jarsDir    = args[1];
        String scriptFile = args[2];
        System.out.println("WEBAPPDIR  = " + webappDir); 
        System.out.println("JARSDIR    = " + jarsDir);
        System.out.println("SCRIPTFILE = " + scriptFile);

        File webappDirFile = new File(webappDir);
        File jarsDirFile = new File(jarsDir);
        if (!webappDirFile.isDirectory() || !jarsDirFile.isDirectory()) {
            System.err.println("ERROR: webapp.dir and jars.dir must specify existing directories");
            System.exit(-1);
        }
       
        // Create new classloader and use it from here on out
        ClassLoader cl = new StandaloneWebappClassLoader(
            webappDir, jarsDir, GroovyRunner.class.getClassLoader());
        Thread.currentThread().setContextClassLoader(cl);
        
        // Apparently bug GROOVY-1194 prevents both of these approaches from working
        
        // Approach #1
        //GroovyShell gshell = new GroovyShell(cl);
        //gshell.evaluate(new File(args[2]));
                
        // Approach #2
        //ClassLoader parent = GroovyRunner.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(cl);
        Class groovyClass = loader.parseClass(new File(scriptFile));
        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
        groovyObject.invokeMethod("run", null);
    }
