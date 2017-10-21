    @Test
    public void testQuoted()
    {
        QuotedCSV values = new QuotedCSV();
        values.addValue("A;p=\"v\",B,\"C, D\"");
        Assert.assertThat(values,Matchers.contains(
                "A;p=\"v\"",
                "B",
                "\"C, D\""));
    }
