    @Test
    public void testOWS()
    {
        QuotedCSV values = new QuotedCSV();
        values.addValue("  value 0.5  ;  pqy = vwz  ;  q =0.5  ,  value 1.0 ,  other ; param ");
        Assert.assertThat(values,Matchers.contains(
                "value 0.5;pqy=vwz;q=0.5",
                "value 1.0",
                "other;param"));
    }
