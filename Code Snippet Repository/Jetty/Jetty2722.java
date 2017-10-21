    @Test
    public void testPropertyUserStoreLoadUpdateUser() throws Exception
    {
        assumeThat("Skipping on OSX", OS.IS_OSX, is(false));
        final UserCount userCount = new UserCount();
        final File usersFile = initUsersText();
        final AtomicInteger loadCount = new AtomicInteger(0);
        PropertyUserStore store = new PropertyUserStore()
        {
            @Override
            protected void loadUsers() throws IOException
            {
                loadCount.incrementAndGet();
                super.loadUsers();
            }
        };
        store.setHotReload(true);
        store.setConfigFile(usersFile);
        store.registerUserListener(userCount);

        store.start();
        
        userCount.assertThatCount(is(3));
        assertThat(loadCount.get(),is(1));
        
        addAdditionalUser(usersFile,"skip: skip, roleA\n");
        userCount.awaitCount(4);
        assertThat(loadCount.get(),is(2));
        assertThat(store.getUserIdentity("skip"), notNullValue());
        userCount.assertThatCount(is(4));
        userCount.assertThatUsers(hasItem("skip"));
        
        if (OS.IS_LINUX)
            Files.createFile(testdir.getPath().toRealPath().resolve("unrelated.txt"),
                PosixFilePermissions.asFileAttribute(EnumSet.noneOf(PosixFilePermission.class)));
        else
            Files.createFile(testdir.getPath().toRealPath().resolve("unrelated.txt"));
        
        Thread.sleep(1100);
        assertThat(loadCount.get(),is(2));

        userCount.assertThatCount(is(4));
        userCount.assertThatUsers(hasItem("skip"));
    }
