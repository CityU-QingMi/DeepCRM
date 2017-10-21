    @Test
    public void testMultiAnnotation() throws Exception
    {
        String[] classNames = new String[]
        { "org.eclipse.jetty.annotations.ClassB" };
        AnnotationParser parser = new AnnotationParser();

        class MultiAnnotationHandler extends AnnotationParser.AbstractHandler
        {
            public void handle(ClassInfo info, String annotation)
            {
                if (annotation == null || ! "org.eclipse.jetty.annotations.Multi".equals(annotation))
                    return;
                assertTrue("org.eclipse.jetty.annotations.ClassB".equals(info.getClassName()));
            }

            public void handle(FieldInfo info, String annotation)
            {                
                if (annotation == null || ! "org.eclipse.jetty.annotations.Multi".equals(annotation))
                    return;
                // there should not be any
                fail();
            }

            public void handle(MethodInfo info, String annotation)
            {  
                if (annotation == null || ! "org.eclipse.jetty.annotations.Multi".equals(annotation))
                    return;
                assertTrue("org.eclipse.jetty.annotations.ClassB".equals(info.getClassInfo().getClassName()));
                assertTrue("a".equals(info.getMethodName()));
            }
        }

        parser.parse(Collections.singleton(new MultiAnnotationHandler()), classNames);
    }
