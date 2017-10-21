    @Test
    public void test() {
        User user = new User();
        user.setLogin( UUID.randomUUID().toString() );

        Customer customer = new Customer();
        customer.setUser( user );

        Assert.assertEquals( customer, user.getCustomer() );

        // check dirty tracking is set automatically with bi-directional association management
        EnhancerTestUtils.checkDirtyTracking( user, "login", "customer" );

        User anotherUser = new User();
        anotherUser.setLogin( UUID.randomUUID().toString() );

        customer.setUser( anotherUser );

        Assert.assertNull( user.getCustomer() );
        Assert.assertEquals( customer, anotherUser.getCustomer() );

        user.setCustomer( new Customer() );

        Assert.assertEquals( user, user.getCustomer().getUser() );
    }
