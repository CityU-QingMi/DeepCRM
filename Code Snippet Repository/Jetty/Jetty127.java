    @Test
    public void testSampleAnnotation() throws Exception
    {
        String[] classNames = new String[]
        { "org.eclipse.jetty.annotations.ClassA" };
        AnnotationParser parser = new AnnotationParser();

        class SampleAnnotationHandler extends AnnotationParser.AbstractHandler
        {
            private List<String> methods = Arrays.asList("a","b","c","d","l");

            public void handle(ClassInfo info, String annotation)
            {
                if (annotation == null || !"org.eclipse.jetty.annotations.Sample".equals(annotation))
                    return;

                assertEquals("org.eclipse.jetty.annotations.ClassA",info.getClassName());
            }

            public void handle(FieldInfo info, String annotation)
            {                
                if (annotation == null || !"org.eclipse.jetty.annotations.Sample".equals(annotation))
                    return;
                assertEquals("m",info.getFieldName());
                assertEquals(org.objectweb.asm.Type.OBJECT,org.objectweb.asm.Type.getType(info.getFieldType()).getSort());
            }

            public void handle(MethodInfo info, String annotation)
            {                
                if (annotation == null || !"org.eclipse.jetty.annotations.Sample".equals(annotation))
                    return;
                assertEquals("org.eclipse.jetty.annotations.ClassA",info.getClassInfo().getClassName());
                assertTrue(methods.contains(info.getMethodName()));
                assertEquals("org.eclipse.jetty.annotations.Sample",annotation);
            }
        }

        //long start = System.currentTimeMillis();
        parser.parse(Collections.singleton(new SampleAnnotationHandler()), classNames);
        //long end = System.currentTimeMillis();

        //System.err.println("Time to parse class: " + ((end - start)));
    }
