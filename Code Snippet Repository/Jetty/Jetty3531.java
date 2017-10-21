    @Test
    public void testMultipleRangesOverlapping()
    {
        String rangeString;

        rangeString = "bytes=5-20,15-25";

        List<InclusiveByteRange> ranges = parseRanges(rangeString,200);
        assertEquals("Satisfiable Ranges of [" + rangeString + "] count",2,ranges.size());
        assertRange("Range [" + rangeString + "]",5,20,200,ranges.get(0));
        assertRange("Range [" + rangeString + "]",15,25,200,ranges.get(1));
    }
