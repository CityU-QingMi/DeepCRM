    private void assertEquals(List<String> expected, List<String> actual)
    {
        Assert.assertThat("Expected Line Count",actual.size(),is(expected.size()));
        int len = expected.size();
        for (int i = 0; i < len; i++)
        {
            String expectedLine = expected.get(i);
            String actualLine = actual.get(i);

            Assert.assertThat("Line[" + i + "]",actualLine,is(expectedLine));
        }
    }
