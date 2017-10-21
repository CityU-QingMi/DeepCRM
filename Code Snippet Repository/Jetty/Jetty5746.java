    @Test
    public void testPrintSource() throws UnsupportedEncodingException
    {
        Properties props=new Properties();
        props.put("test.SOURCE","true");
        StdErrLog log = new StdErrLog("test",props);
        log.setLevel(StdErrLog.LEVEL_DEBUG);

        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(test);
        log.setStdErrStream(err);

        log.debug("Show me the source!");

        String output = new String(test.toByteArray(), StandardCharsets.UTF_8);
        // System.err.print(output);

        Assert.assertThat(output, containsString(".StdErrLogTest#testPrintSource(StdErrLogTest.java:"));
        

        props.put("test.SOURCE","false");
        log=new StdErrLog("other",props);
    }
