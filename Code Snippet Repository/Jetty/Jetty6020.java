    @Test
    public void testSystemServerClass() throws Exception
    {
        String[] oldServC=_context.getServerClasses();
        String[] newServC=new String[oldServC.length+1];
        newServC[0]="org.eclipse.jetty.webapp.Configuration";
        System.arraycopy(oldServC,0,newServC,1,oldServC.length);
        _context.setServerClasses(newServC);

        String[] oldSysC=_context.getSystemClasses();
        String[] newSysC=new String[oldSysC.length+1];
        newSysC[0]="org.eclipse.jetty.webapp.";
        System.arraycopy(oldSysC,0,newSysC,1,oldSysC.length);
        _context.setSystemClasses(newSysC);

        assertCanLoadClass("org.acme.webapp.ClassInJarA");
        assertCanLoadClass("org.acme.webapp.ClassInJarB");
        assertCanLoadClass("org.acme.other.ClassInClassesC");
        assertCantLoadClass("org.eclipse.jetty.webapp.Configuration");
        assertCantLoadClass("org.eclipse.jetty.webapp.JarScanner");

        oldSysC=_context.getSystemClasses();
        newSysC=new String[oldSysC.length+1];
        newSysC[0]="org.acme.webapp.ClassInJarA";
        System.arraycopy(oldSysC,0,newSysC,1,oldSysC.length);
        _context.setSystemClasses(newSysC);

        assertCanLoadResource("org/acme/webapp/ClassInJarA.class");
        _context.setSystemClasses(oldSysC);

        oldServC=_context.getServerClasses();
        newServC=new String[oldServC.length+1];
        newServC[0]="org.acme.webapp.ClassInJarA";
        System.arraycopy(oldServC,0,newServC,1,oldServC.length);
        _context.setServerClasses(newServC);
        assertCanLoadResource("org/acme/webapp/ClassInJarA.class");
    }
