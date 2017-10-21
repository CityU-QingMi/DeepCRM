    @Test
    public void testBasedirExclusion() throws Exception
    {
        // Build up basedir, which itself has a path segment that violates java package and classnaming.
        // The basedir should have no effect on annotation scanning.
        // Intentionally using a base director name that starts with a "."
        // This mimics what you see in jenkins, hudson, hadoop, solr, camel, and selenium for their 
        // installed and/or managed webapps
        File basedir = testdir.getPathFile(".base/workspace/classes").toFile();
        FS.ensureEmpty(basedir);

        // Copy in class that is known to have annotations.
        copyClass(ClassA.class,basedir);

        // Setup Tracker
        TrackingAnnotationHandler tracker = new TrackingAnnotationHandler(Sample.class.getName());

        // Setup annotation scanning
        AnnotationParser parser = new AnnotationParser();
        
        // Parse
        parser.parse(Collections.singleton(tracker), basedir.toURI());
        
        // Validate
        Assert.assertThat("Found Class", tracker.foundClasses, contains(ClassA.class.getName()));
    }
