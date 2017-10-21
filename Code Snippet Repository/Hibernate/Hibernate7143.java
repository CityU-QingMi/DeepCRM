    @Test
    public void test() {
        Employee charles = new Employee( "Charles", "Engineer" );
        charles.setOca( 1002 );

        // Check that both types of class attributes are being dirty tracked
        checkDirtyTracking( charles, "title", "oca" );
        clearDirtyTracking( charles );

        // Let's give charles a promotion, this time using method references
        charles.setOca( 99 );
        charles.setTitle( "Manager" );

        checkDirtyTracking( charles, "title", "oca" );

        Contractor bob = new Contractor( "Bob", 100 );
        bob.setOca( 1003 );

        // Check that both types of class attributes are being dirty tracked
        checkDirtyTracking( bob, "rate", "oca" );
        clearDirtyTracking( bob );

        // Let's give bob a rate increase, this time using method references
        bob.setOca( 88 );
        bob.setRate( 200 );

        checkDirtyTracking( bob, "rate", "oca" );
    }
