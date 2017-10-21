    @Test
    public void testMultipleRangesSplit()
    {
        String rangeString;
        rangeString = "bytes=5-10,15-20";

        List<InclusiveByteRange> ranges = parseRanges(rangeString,200);
        assertEquals("Satisfiable Ranges of [" + rangeString + "] count",2,ranges.size());
        assertRange("Range [" + rangeString + "]",5,10,200,ranges.get(0));
        assertRange("Range [" + rangeString + "]",15,20,200,ranges.get(1));
    }
