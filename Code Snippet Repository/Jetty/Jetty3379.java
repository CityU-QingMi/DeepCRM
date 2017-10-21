    @Test
    public void testEmpty() throws Exception
    {
        Assert.assertThat(_in.available(), Matchers.equalTo(0));
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
