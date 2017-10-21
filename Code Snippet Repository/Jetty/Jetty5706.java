    @Test
    public void testAddBean_AddListener_Start() throws Exception
    {
        Foo foo = new Foo();
        Bar bara = new Bar("a");
        Bar barb = new Bar("b");
        foo.addBean(bara);
        foo.addBean(barb);

        CapturingListener listener = new CapturingListener();
        foo.addLifeCycleListener(listener);
        if(WORKAROUND)
            foo.addEventListener(listener);

        try
        {
            foo.start();

            assertThat("Foo.started",foo.isStarted(),is(true));
            assertThat("Bar(a).started",bara.isStarted(),is(true));
            assertThat("Bar(b).started",barb.isStarted(),is(true));

            listener.assertEvents(hasItem("Foo - STARTING"));
            listener.assertEvents(hasItem("Foo - STARTED"));
            listener.assertEvents(hasItem("Bar(a) - STARTING"));
            listener.assertEvents(hasItem("Bar(a) - STARTED"));
            listener.assertEvents(hasItem("Bar(b) - STARTING"));
            listener.assertEvents(hasItem("Bar(b) - STARTED"));
        }
        finally
        {
            foo.stop();
        }
    }
