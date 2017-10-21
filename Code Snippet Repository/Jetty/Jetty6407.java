    private void assertSplitAt(Iterator<String> iter, String... expectedParts)
    {
        int len = expectedParts.length;
        for (int i = 0; i < len; i++)
        {
            String expected = expectedParts[i];
            Assert.assertThat("Split[" + i + "].hasNext()",iter.hasNext(),is(true));
            Assert.assertThat("Split[" + i + "].next()",iter.next(),is(expected));
        }
    }
