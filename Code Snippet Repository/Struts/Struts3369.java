    public void testCustomDateFormat() throws Exception {
        JSONResult result = new JSONResult();
        result.setDefaultDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

        SingleDateBean dateBean = new SingleDateBean();
        dateBean.setDate(sdf.parse("2012-12-23 10:10:10 GMT"));

        stack.push(dateBean);

        this.invocation.setAction(dateBean);
        result.execute(this.invocation);

        String out = response.getContentAsString();
        assertEquals("{\"date\":\"12-23-2012\"}", out);
    }
