    @Test
    public void testPropertyUserStoreLoadRemoveUser() throws Exception
    {
        assumeThat("Skipping on OSX", OS.IS_OSX, is(false));
        final UserCount userCount = new UserCount();
        // initial user file (3) users
        final File usersFile = initUsersText();
        
        // adding 4th user
        addAdditionalUser(usersFile,"skip: skip, roleA\n");

        PropertyUserStore store = new PropertyUserStore();
        store.setHotReload(true);
        store.setConfigFile(usersFile);

        store.registerUserListener(userCount);

        store.start();

        userCount.assertThatCount(is(4));

        // rewrite file with original 3 users
        initUsersText();
        
        userCount.awaitCount(3);
    }
