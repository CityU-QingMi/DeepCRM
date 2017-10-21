    public Servlet load(String location) throws Exception {
        location = StringUtils.substringBeforeLast(location, "?");

        LOG.debug("Compiling JSP [{}]", location);

        //use Jasper to compile the JSP into java code
        JspC jspC = compileJSP(location);
        String source = jspC.getSourceCode();

        //System.out.print(source);

        String className = extractClassName(source);

        //use Java Compiler API to compile the java code into a class
        //the tlds that were discovered are added (their jars) to the classpath
        compileJava(className, source, jspC.getTldAbsolutePaths());

        //load the class that was just built
        Class clazz = Class.forName(className, false, classLoader);
        return createServlet(clazz);
    }
