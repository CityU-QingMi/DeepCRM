    @Test
    public void testDomain()
    {
        // given
        String domain = "Test";

        // when
        mbeanContainer.setDomain(domain);

        // then
        Assert.assertEquals("Domain name must be Test", domain, mbeanContainer.getDomain());
    }
