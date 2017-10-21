    @Test
    public void testUploadWithOutputStreamFailureToConnect() throws Exception
    {
        start(new EmptyServerHandler());

        final byte[] data = new byte[512];
        final CountDownLatch latch = new CountDownLatch(1);
        OutputStreamContentProvider content = new OutputStreamContentProvider();
        client.newRequest("0.0.0.1", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .send(result ->
                {
                    if (result.isFailed())
                        latch.countDown();
                });

        try (OutputStream output = content.getOutputStream())
        {
            output.write(data);
            Assert.fail();
        }
        catch (IOException x)
        {
            // Expected
        }

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
