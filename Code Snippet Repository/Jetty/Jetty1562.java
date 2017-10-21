    @Test
    public void testQuotedNoQuotes()
    {
        QuotedCSV values = new QuotedCSV(false);
        values.addValue("A;p=\"v\",B,\"C, D\"");
        Assert.assertThat(values,Matchers.contains(
                "A;p=v",
                "B",
                "C, D"));
    }
