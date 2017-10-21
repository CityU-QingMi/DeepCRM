    @Test
    public void test7231_5_3_4_example1()
    {
        QuotedQualityCSV values = new QuotedQualityCSV();
        values.addValue("compress, gzip");
        values.addValue("");
        values.addValue("*");
        values.addValue("compress;q=0.5, gzip;q=1.0");
        values.addValue("gzip;q=1.0, identity; q=0.5, *;q=0");
        
        Assert.assertThat(values,Matchers.contains(
                "compress",
                "gzip",
                "*",
                "gzip",
                "gzip",
                "compress",
                "identity"
                ));
    }
