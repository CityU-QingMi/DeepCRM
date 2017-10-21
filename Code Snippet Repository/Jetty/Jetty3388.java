    @Test
    public void testBlockingEOF() throws Exception
    {
        new Thread(() ->
        {
            try
            {
                Thread.sleep(500);
                _in.eof();
            }
            catch (Throwable th)
            {
                th.printStackTrace();
            }
        }).start();

        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_in.read(), Matchers.equalTo(-1));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(true));

        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("blockForContent"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
