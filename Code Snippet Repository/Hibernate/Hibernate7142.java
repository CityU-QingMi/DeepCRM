    @Test
    public void test() {
        User user = new User();
        user.login = UUID.randomUUID().toString();

        Customer customer = new Customer();
        customer.user = user;

        assertEquals( customer, getFieldByReflection( user, "customer" ) );

        // check dirty tracking is set automatically with bi-directional association management
        EnhancerTestUtils.checkDirtyTracking( user, "login", "customer" );

        User anotherUser = new User();
        anotherUser.login = UUID.randomUUID().toString();

        customer.user = anotherUser;

        Assert.assertNull( user.customer );
        assertEquals( customer, getFieldByReflection( anotherUser, "customer" ) );

        user.customer = new Customer();
        assertEquals( user, user.customer.user );
    }
