    @Test
    public void testDeclareRoles ()
    throws Exception
    {
        WebAppContext wac = new WebAppContext();
        ConstraintSecurityHandler sh = new ConstraintSecurityHandler();
        wac.setSecurityHandler(sh);
        sh.setRoles(new HashSet<String>(Arrays.asList(new String[]{"humpty", "dumpty"})));
        DeclareRolesAnnotationHandler handler = new DeclareRolesAnnotationHandler(wac);
        handler.doHandle(ServletC.class);
        assertTrue(sh.getRoles().contains("alice"));
        assertTrue(sh.getRoles().contains("humpty"));
        assertTrue(sh.getRoles().contains("dumpty"));
    }
