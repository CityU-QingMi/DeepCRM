    @Test
    public void test() {
        Group group = new Group();
        Group anotherGroup = new Group();

        User user = new User();
        User anotherUser = new User();

        user.addGroup( group );
        user.addGroup( anotherGroup );
        anotherUser.addGroup( group );

        Assert.assertEquals( 2, group.getUsers().size() );
        Assert.assertEquals( 1, anotherGroup.getUsers().size() );

        group.resetUsers();

        Assert.assertEquals( 1, user.getGroups().size() );
        Assert.assertEquals( 0, anotherUser.getGroups().size() );

        // Test remove
        user.addGroup( group );
        anotherUser.addGroup( group );

        Assert.assertEquals( 2, group.getUsers().size() );
        Assert.assertEquals( 1, anotherGroup.getUsers().size() );

        Set<Group> groups = new HashSet<>( user.getGroups() );
        groups.remove( group );
        user.setGroups( groups );

        Assert.assertEquals( 1, group.getUsers().size() );
        Assert.assertEquals( 1, anotherGroup.getUsers().size() );

        groups.remove( anotherGroup );
        user.setGroups( groups );

        Assert.assertEquals( 1, group.getUsers().size() );
        // This happens (and is expected) because there was no snapshot taken before remove
        Assert.assertEquals( 1, anotherGroup.getUsers().size() );
    }
