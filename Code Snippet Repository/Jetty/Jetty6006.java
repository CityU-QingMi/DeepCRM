    @Test
    public void testCircular1 ()
    throws Exception
    {

        //A: after B
        //B: after A
        List<Resource> resources = new ArrayList<Resource>();
        MetaData metaData = new MetaData();
        metaData._ordering = new RelativeOrdering(metaData);

        //A: after B
        TestResource jar1 = new TestResource("A");
        resources.add(jar1);
        TestResource r1 = new TestResource("A/web-fragment.xml");
        FragmentDescriptor f1 = new FragmentDescriptor(r1);
        f1._name = "A";
        metaData._webFragmentNameMap.put(f1._name, f1);
        metaData._webFragmentResourceMap.put(jar1, f1);
        //((RelativeOrdering)metaData._ordering).addNoOthers(f1);
        f1._otherType = FragmentDescriptor.OtherType.None;
        f1._afters.add("B");

        //B: after A
        TestResource jar2 = new TestResource("B");
        resources.add(jar2);
        TestResource r2 = new TestResource("B/web-fragment.xml");
        FragmentDescriptor f2 = new FragmentDescriptor(r2);
        f2._name="B";
        metaData._webFragmentNameMap.put(f2._name, f2);
        metaData._webFragmentResourceMap.put(jar2, f2);
        //((RelativeOrdering)metaData._ordering).addNoOthers(f2);
        f2._otherType = FragmentDescriptor.OtherType.None;
        f2._afters.add("A");

        try
        {
            metaData._ordering.order(resources);
            fail("No circularity detected");
        }
        catch (Exception e)
        {
            assertTrue (e instanceof IllegalStateException);
        }
    }
