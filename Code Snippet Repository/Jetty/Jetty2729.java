    @Test
    public void addUser()
    {
        this.userStore.addUser( "foo", Credential.getCredential( "beer" ), new String[]{"pub"} );
        Assert.assertEquals(1, this.userStore.getKnownUserIdentities().size());
        UserIdentity userIdentity = this.userStore.getUserIdentity( "foo" );
        Assert.assertNotNull( userIdentity );
        Assert.assertEquals( "foo", userIdentity.getUserPrincipal().getName() );
        Set<AbstractLoginService.RolePrincipal>
            roles = userIdentity.getSubject().getPrincipals( AbstractLoginService.RolePrincipal.class);
        List<String> list = roles.stream()
            .map( rolePrincipal -> rolePrincipal.getName() )
            .collect( Collectors.toList() );
        Assert.assertEquals(1, list.size());
        Assert.assertEquals( "pub", list.get( 0 ) );
    }
