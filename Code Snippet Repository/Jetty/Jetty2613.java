    @Test
    public void testWildcardVirtualHosts() throws Exception
    {
        checkWildcardHost(true,null,new String[] {"foo.com", ".foo.com", "vhost.foo.com"});
        checkWildcardHost(true,new String[] {null},new String[] {"foo.com", ".foo.com", "vhost.foo.com"});

        checkWildcardHost(true,new String[] {"foo.com", "*.foo.com"}, new String[] {"foo.com", ".foo.com", "vhost.foo.com"});
        checkWildcardHost(false,new String[] {"foo.com", "*.foo.com"}, new String[] {"badfoo.com", ".badfoo.com", "vhost.badfoo.com"});

        checkWildcardHost(false,new String[] {"*."}, new String[] {"anything.anything"});

        checkWildcardHost(true,new String[] {"*.foo.com"}, new String[] {"vhost.foo.com", ".foo.com"});
        checkWildcardHost(false,new String[] {"*.foo.com"}, new String[] {"vhost.www.foo.com", "foo.com", "www.vhost.foo.com"});

        checkWildcardHost(true,new String[] {"*.sub.foo.com"}, new String[] {"vhost.sub.foo.com", ".sub.foo.com"});
        checkWildcardHost(false,new String[] {"*.sub.foo.com"}, new String[] {".foo.com", "sub.foo.com", "vhost.foo.com"});

        checkWildcardHost(false,new String[] {"foo.*.com","foo.com.*"}, new String[] {"foo.vhost.com", "foo.com.vhost", "foo.com"});
    }
