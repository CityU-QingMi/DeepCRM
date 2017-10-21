    @Test
    public void testOrderFragments() throws Exception 
    {
        final MetaData metadata = new MetaData();
        final Resource jarResource = new TestResource("A");

        metadata.setOrdering(new RelativeOrdering(metadata));
        metadata.addWebInfJar(jarResource);
        metadata.orderFragments();
        assertEquals(1, metadata.getOrderedWebInfJars().size());
        metadata.orderFragments();
        assertEquals(1, metadata.getOrderedWebInfJars().size());
    }
