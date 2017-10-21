    @Test
    public void testDecoders() throws Exception
    {
        List<Class<? extends Decoder>> decoders = config.getDecoders();
        Assert.assertThat("Decoders",decoders,notNullValue());

        Class<?> expectedClass = DateDecoder.class;
        boolean hasExpectedDecoder = false;
        for (Class<? extends Decoder> decoder : decoders)
        {
            if (expectedClass.isAssignableFrom(decoder))
            {
                hasExpectedDecoder = true;
            }
        }

        Assert.assertTrue("Client Decoders has " + expectedClass.getName(),hasExpectedDecoder);
    }
