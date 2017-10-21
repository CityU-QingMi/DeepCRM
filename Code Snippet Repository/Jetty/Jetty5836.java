    @Test
    public void testResourceTsWrongPWResourceKs() throws Exception
    {
        Resource keystoreResource = Resource.newSystemResource("keystore");
        Resource truststoreResource = Resource.newSystemResource("keystore");

        cf.setKeyStoreResource(keystoreResource);
        cf.setTrustStoreResource(truststoreResource);
        cf.setKeyStorePassword("storepwd");
        cf.setKeyManagerPassword("keypwd");
        cf.setTrustStorePassword("wrong_storepwd");

        try (StacklessLogging stackless = new StacklessLogging(AbstractLifeCycle.class))
        {
            cf.start();
            Assert.fail();
        }
        catch(IOException e)
        {
            Assert.assertThat(e.toString(),Matchers.containsString("java.io.IOException: Keystore was tampered with, or password was incorrect"));
        }
    }
