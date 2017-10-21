    public void testFilesAreReadOnlyOnce() throws Exception {
        //make sure that files are not read multiple times
        String jsp = "org/apache/struts2/dont-use.jsp";

        CountingClassLoaderInterface classLoaderInterface = new CountingClassLoaderInterface(this.getClass().getClassLoader());
        context.setAttribute(ClassLoaderInterface.CLASS_LOADER_INTERFACE, classLoaderInterface);
        result.setLocation(jsp);

        result.execute(null);
        Integer counter0 = classLoaderInterface.counters.get(jsp);
        assertNotNull(counter0);

        result.execute(null);
        Integer counter1 = classLoaderInterface.counters.get(jsp);
        assertNotNull(counter1);

        assertEquals(counter0, counter1);
    }
