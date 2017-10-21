    @Test
    public void testParseClassNames() throws Exception
    {
        classNames.add(ClassA.class.getName());
        classNames.add(ClassB.class.getName());

        SampleHandler handler = new SampleHandler();
        AnnotationParser parser = new AnnotationParser();
        parser.parse(Collections.singleton(handler), classNames);

        //check we got  2 class annotations
        assertEquals(2, handler.annotatedClassNames.size());

        //check we got all annotated methods on each class
        assertEquals (7, handler.annotatedMethods.size());
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassA.a"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassA.b"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassA.c"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassA.d"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassA.l"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassB.a"));
        assertTrue (handler.annotatedMethods.contains("org.eclipse.jetty.annotations.ClassB.c"));

        //check we got all annotated fields on each class
        assertEquals(1, handler.annotatedFields.size());
        assertEquals("org.eclipse.jetty.annotations.ClassA.m", handler.annotatedFields.get(0));
    }
