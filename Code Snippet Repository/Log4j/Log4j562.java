    @Test
    public void testBoxLong() throws Exception {
        assertEquals("0", Unbox.box(0L).toString());
        assertEquals("1", Unbox.box(1L).toString());
        assertEquals("127", Unbox.box(127L).toString());
        assertEquals("-1", Unbox.box(-1L).toString());
        assertEquals("-128", Unbox.box(-128L).toString());
        assertEquals(new Long(Long.MAX_VALUE).toString(), Unbox.box(Long.MAX_VALUE).toString());
        assertEquals(new Long(Long.MIN_VALUE).toString(), Unbox.box(Long.MIN_VALUE).toString());
    }
