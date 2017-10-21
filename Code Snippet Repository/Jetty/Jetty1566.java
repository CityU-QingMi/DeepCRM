    @Test
    public void test7231_5_3_2_example4()
    {
        QuotedQualityCSV values = new QuotedQualityCSV();
        values.addValue("text/*;q=0.3, text/html;q=0.7, text/html;level=1,");
        values.addValue("text/html;level=2;q=0.4, */*;q=0.5");
        Assert.assertThat(values,Matchers.contains(
                "text/html;level=1",
                "text/html",
                "*/*",
                "text/html;level=2",
                "text/*"
                ));
    }
