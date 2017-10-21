    @Test
    public void testTypeInheritanceHandling() throws Exception
    {
        Map<String, Set<String>> map = new ConcurrentHashMap<>();
        
        AnnotationParser parser = new AnnotationParser();
        ClassInheritanceHandler handler = new ClassInheritanceHandler(map);

        class Foo implements InterfaceD
        {
        }

        classNames.clear();
        classNames.add(ClassA.class.getName());
        classNames.add(ClassB.class.getName());
        classNames.add(InterfaceD.class.getName());
        classNames.add(Foo.class.getName());

        parser.parse(Collections.singleton(handler), classNames);

        assertNotNull(map);
        assertFalse(map.isEmpty());
        assertEquals(2, map.size());
      
        
        assertTrue (map.keySet().contains("org.eclipse.jetty.annotations.ClassA"));
        assertTrue (map.keySet().contains("org.eclipse.jetty.annotations.InterfaceD"));
        Set<String> classes = map.get("org.eclipse.jetty.annotations.ClassA");
        assertEquals(1, classes.size());
        assertEquals ("org.eclipse.jetty.annotations.ClassB", classes.iterator().next());

        classes = map.get("org.eclipse.jetty.annotations.InterfaceD");
        assertEquals(2, classes.size());
        assertTrue(classes.contains("org.eclipse.jetty.annotations.ClassB"));
        assertTrue(classes.contains(Foo.class.getName()));
    }
