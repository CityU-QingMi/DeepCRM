    @Test
    @SuppressWarnings("")
    public void testToStringArray()
    {
        assertEquals(0,LazyList.toStringArray(null).length);

        assertEquals(1,LazyList.toStringArray("a").length);
        assertEquals("a",LazyList.toStringArray("a")[0]);

        @SuppressWarnings("rawtypes")
        ArrayList l=new ArrayList();
        l.add("a");
        l.add(null);
        l.add(new Integer(2));
        String[] a=LazyList.toStringArray(l);

        assertEquals(3,a.length);
        assertEquals("a",a[0]);
        assertEquals(null,a[1]);
        assertEquals("2",a[2]);

    }
