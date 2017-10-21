    @Test
    public void testGetFragmentFromJar() throws Exception
    {
        String dir = MavenTestingUtils.getTargetTestingDir("getFragmentFromJar").getAbsolutePath();
        File file = new File(dir);
        file=new File(file.getCanonicalPath());
        URL url=file.toURL();

        Resource jar1 = Resource.newResource(url+"file.jar");

        AnnotationConfiguration config = new AnnotationConfiguration();
        WebAppContext wac = new WebAppContext();

        List<FragmentDescriptor> frags = new ArrayList<FragmentDescriptor>();
        frags.add(new FragmentDescriptor(Resource.newResource("jar:"+url+"file.jar!/fooa.props")));
        frags.add(new FragmentDescriptor(Resource.newResource("jar:"+url+"file2.jar!/foob.props")));

        assertNotNull(config.getFragmentFromJar(jar1, frags));
    }
