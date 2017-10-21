    @Test
    public void testBlockingRead() throws Exception
    {
        new Thread(() ->
        {
            try
            {
                Thread.sleep(500);
                _in.addContent(new TContent("AB"));
            }
            catch (Throwable th)
            {
                th.printStackTrace();
            }
        }).start();

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'A'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("blockForContent"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo((int)'B'));

        Assert.assertThat(_history.poll(), Matchers.equalTo("Content succeeded AB"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
