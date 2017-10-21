    @Test
    public void testBoxInt() throws Exception {
        assertEquals("0", Unbox.box(0).toString());
        assertEquals("1", Unbox.box(1).toString());
        assertEquals("127", Unbox.box(127).toString());
        assertEquals("-1", Unbox.box(-1).toString());
        assertEquals("-128", Unbox.box(-128).toString());
        assertEquals(new Integer(Integer.MAX_VALUE).toString(), Unbox.box(Integer.MAX_VALUE).toString());
        assertEquals(new Integer(Integer.MIN_VALUE).toString(), Unbox.box(Integer.MIN_VALUE).toString());
    }
