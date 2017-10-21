    @Test
    public void testAsyncEOF() throws Exception
    {
        _in.setReadListener(_listener);
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onReadUnready"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        _in.eof();
        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onDataAvailable"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.read(), Matchers.equalTo(-1));
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(true));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
