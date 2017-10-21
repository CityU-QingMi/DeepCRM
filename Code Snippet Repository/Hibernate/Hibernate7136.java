    @Test
    public void test() {
        Customer customer = new Customer();
        Assert.assertTrue( customer.getInventories().isEmpty() );

        CustomerInventory customerInventory = new CustomerInventory();
        customerInventory.setCustomer( customer );

        Assert.assertEquals( 1, customer.getInventories().size() );
        Assert.assertTrue( customer.getInventories().contains( customerInventory ) );

        Customer anotherCustomer = new Customer();
        Assert.assertTrue( anotherCustomer.getInventories().isEmpty() );
        customerInventory.setCustomer( anotherCustomer );

        Assert.assertTrue( customer.getInventories().isEmpty() );
        Assert.assertEquals( 1, anotherCustomer.getInventories().size() );
        Assert.assertSame( customerInventory, anotherCustomer.getInventories().get( 0 ) );

        customer.addInventory( customerInventory );

        Assert.assertSame( customer, customerInventory.getCustomer() );
        Assert.assertTrue( anotherCustomer.getInventories().isEmpty() );
        Assert.assertEquals( 1, customer.getInventories().size() );

        customer.addInventory( new CustomerInventory() );
        Assert.assertEquals( 2, customer.getInventories().size() );

        // Test remove
        customer.removeInventory( customerInventory );
        Assert.assertEquals( 1, customer.getInventories().size() );

        // This happens (and is expected) because there was no snapshot taken before remove
        Assert.assertNotNull( customerInventory.getCustomer() );
    }
