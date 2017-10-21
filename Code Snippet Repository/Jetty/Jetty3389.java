    @Test
    public void testAsyncEmpty() throws Exception
    {
        _in.setReadListener(_listener);
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onReadUnready"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
