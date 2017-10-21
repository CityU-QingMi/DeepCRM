    @Test
    public void testBoxShort() throws Exception {
        assertEquals("0", Unbox.box((short) 0).toString());
        assertEquals("1", Unbox.box((short) 1).toString());
        assertEquals("127", Unbox.box((short) 127).toString());
        assertEquals("-1", Unbox.box((short) -1).toString());
        assertEquals("-128", Unbox.box((short) -128).toString());
        assertEquals(new Short(Short.MAX_VALUE).toString(), Unbox.box(Short.MAX_VALUE).toString());
        assertEquals(new Short(Short.MIN_VALUE).toString(), Unbox.box(Short.MIN_VALUE).toString());
    }
