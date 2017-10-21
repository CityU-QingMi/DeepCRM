    @Test
    public void testExposedClass() throws Exception
    {
        String[] oldSC=_context.getServerClasses();
        String[] newSC=new String[oldSC.length+1];
        newSC[0]="-org.eclipse.jetty.webapp.Configuration";
        System.arraycopy(oldSC,0,newSC,1,oldSC.length);
        _context.setServerClasses(newSC);

        assertCanLoadClass("org.acme.webapp.ClassInJarA");
        assertCanLoadClass("org.acme.webapp.ClassInJarB");
        assertCanLoadClass("org.acme.other.ClassInClassesC");

        assertCanLoadClass("org.eclipse.jetty.webapp.Configuration");
        assertCantLoadClass("org.eclipse.jetty.webapp.JarScanner");
    }
