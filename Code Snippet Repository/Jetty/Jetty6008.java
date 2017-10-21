    @Test
    public void testRelativeOrderingWithPlainJars2 ()
    throws Exception
    {
        //web.xml has no ordering, jar A has fragment after others, jar B is plain, jar C is plain
        List<Resource> resources = new ArrayList<Resource>();
        MetaData metaData = new MetaData();
        metaData._ordering = new RelativeOrdering(metaData);
        
        //A has after others
        TestResource jar1 = new TestResource("A");
        resources.add(jar1);
        TestResource r1 = new TestResource("A/web-fragment.xml");
        FragmentDescriptor f1 = new FragmentDescriptor(r1);
        f1._name = "A";
        metaData._webFragmentNameMap.put(f1._name, f1);
        metaData._webFragmentResourceMap.put(jar1, f1);
        f1._otherType = FragmentDescriptor.OtherType.After;
        
        //No fragment jar B
        TestResource r4 = new TestResource("plainB");
        resources.add(r4);
        
        //No fragment jar C
        TestResource r5 = new TestResource("plainC");
        resources.add(r5);
        
        List<Resource> orderedList = metaData._ordering.order(resources);
        String[] outcomes = {"plainBplainCA"};
        String result = "";
        for (Resource r:orderedList)
           result+=(((TestResource)r)._name);
        
        if (!checkResult(result, outcomes))
            fail ("No outcome matched "+result);
    }
