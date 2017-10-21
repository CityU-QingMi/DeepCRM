    @Test
    public void testPropertyUserStoreLoadFromJarFile() throws Exception
    {
        final UserCount userCount = new UserCount();
        final String usersFile = initUsersPackedFileText();

        PropertyUserStore store = new PropertyUserStore();
        store.setConfig(usersFile);

        store.registerUserListener(userCount);

        store.start();

        assertThat("Failed to retrieve UserIdentity directly from PropertyUserStore", //
                   store.getUserIdentity("tom"), notNullValue());
        assertThat("Failed to retrieve UserIdentity directly from PropertyUserStore", //
                   store.getUserIdentity("dick"), notNullValue());
        assertThat("Failed to retrieve UserIdentity directly from PropertyUserStore", //
                   store.getUserIdentity("harry"), notNullValue());
        userCount.assertThatCount(is(3));
        userCount.awaitCount(3);
    }
