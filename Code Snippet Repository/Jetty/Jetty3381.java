    @Test
    public void testAsyncError() throws Exception
    {
        _in.setReadListener(_listener);
        Assert.assertThat(_history.poll(), Matchers.equalTo("produceContent 0"));
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onReadUnready"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(false));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        _in.failed(new TimeoutException());
        Assert.assertThat(_history.poll(), Matchers.equalTo("s.onDataAvailable"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        _in.run();
        Assert.assertThat(_in.isFinished(), Matchers.equalTo(true));
        Assert.assertThat(_history.poll(), Matchers.equalTo("l.onError:java.util.concurrent.TimeoutException"));
        Assert.assertThat(_history.poll(), Matchers.nullValue());

        Assert.assertThat(_in.isReady(), Matchers.equalTo(true));
        try
        {
            _in.read();
            Assert.fail();
        }
        catch (IOException e)
        {
            Assert.assertThat(e.getCause(), Matchers.instanceOf(TimeoutException.class));
            Assert.assertThat(_in.isFinished(), Matchers.equalTo(true));
        }

        Assert.assertThat(_history.poll(), Matchers.nullValue());
    }
