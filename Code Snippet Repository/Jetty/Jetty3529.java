    @Test
    public void testMultipleAbsoluteRanges()
    {
        int size = 50;
        String rangeString;

        rangeString = "bytes=5-20,35-65";

        List<InclusiveByteRange> ranges = parseRanges(rangeString,size);
        assertEquals("Satisfiable Ranges of [" + rangeString + "] count",2,ranges.size());
        assertRange("Range [" + rangeString + "]",5,20,size,ranges.get(0));
        assertRange("Range [" + rangeString + "]",35,49,size,ranges.get(1));
    }
