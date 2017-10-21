    @Test
    public void removeUser()
    {
        this.userStore.addUser( "foo", Credential.getCredential( "beer" ), new String[]{"pub"} );
        Assert.assertEquals(1, this.userStore.getKnownUserIdentities().size());
        UserIdentity userIdentity = this.userStore.getUserIdentity( "foo" );
        Assert.assertNotNull( userIdentity );
        Assert.assertEquals( "foo", userIdentity.getUserPrincipal().getName() );
        userStore.removeUser( "foo" );
        userIdentity = this.userStore.getUserIdentity( "foo" );
        Assert.assertNull( userIdentity );
    }
