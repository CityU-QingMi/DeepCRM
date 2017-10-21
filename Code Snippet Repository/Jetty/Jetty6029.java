    @Test
    public void testAlias() throws Exception
    {
        File dir = File.createTempFile("dir",null);
        dir.delete();
        dir.mkdir();
        dir.deleteOnExit();

        File webinf = new File(dir,"WEB-INF");
        webinf.mkdir();

        File classes = new File(dir,"classes");
        classes.mkdir();

        File someclass = new File(classes,"SomeClass.class");
        someclass.createNewFile();

        WebAppContext context = new WebAppContext();
        context.setBaseResource(new ResourceCollection(dir.getAbsolutePath()));

        context.setResourceAlias("/WEB-INF/classes/", "/classes/");

        assertTrue(Resource.newResource(context.getServletContext().getResource("/WEB-INF/classes/SomeClass.class")).exists());
        assertTrue(Resource.newResource(context.getServletContext().getResource("/classes/SomeClass.class")).exists());

    }
