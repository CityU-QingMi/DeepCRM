    @Test
    public void testAbsoluteOrdering3 ()
    throws Exception
    {
        //empty <absolute-ordering>

        MetaData metaData = new MetaData();
        metaData._ordering = new AbsoluteOrdering(metaData);
        List<Resource> resources = new ArrayList<Resource>();

        resources.add(new TestResource("A"));
        resources.add(new TestResource("B"));

        List<Resource> list = metaData._ordering.order(resources);
        assertTrue(list.isEmpty());
    }
