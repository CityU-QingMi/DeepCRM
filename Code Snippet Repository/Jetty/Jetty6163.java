    @Test
    public void testEncoders() throws Exception
    {
        List<Class<? extends Encoder>> encoders = config.getEncoders();
        Assert.assertThat("Encoders",encoders,notNullValue());

        Class<?> expectedClass = TimeEncoder.class;
        boolean hasExpectedEncoder = false;
        for (Class<? extends Encoder> encoder : encoders)
        {
            if (expectedClass.isAssignableFrom(encoder))
            {
                hasExpectedEncoder = true;
            }
        }

        Assert.assertTrue("Client Encoders has " + expectedClass.getName(),hasExpectedEncoder);
    }
