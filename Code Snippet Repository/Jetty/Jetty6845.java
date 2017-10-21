    @Test
    public void testEndsWithTailBytes()
    {
        assertEndsWithTail("11223344",false);
        assertEndsWithTail("00",false);
        assertEndsWithTail("0000",false);
        assertEndsWithTail("FFFF0000",false);
        assertEndsWithTail("880000FFFF",true);
        assertEndsWithTail("0000FFFF",true);
    }
