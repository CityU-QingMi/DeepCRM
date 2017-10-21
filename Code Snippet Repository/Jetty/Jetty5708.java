    @Test
    public void testAddListener_Start_AddBean() throws Exception
    {
        Foo foo = new Foo();
        Bar bara = new Bar("a");
        Bar barb = new Bar("b");

        CapturingListener listener = new CapturingListener();
        foo.addLifeCycleListener(listener);
        if(WORKAROUND)
            foo.addEventListener(listener);

        try
        {
            foo.start();

            listener.assertEvents(hasItem("Foo - STARTING"));
            listener.assertEvents(hasItem("Foo - STARTED"));

            foo.addBean(bara);
            foo.addBean(barb);

            bara.start();
            barb.start();

            assertThat("Bar(a).started",bara.isStarted(),is(true));
            assertThat("Bar(b).started",barb.isStarted(),is(true));

            listener.assertEvents(hasItem("Bar(a) - STARTING"));
            listener.assertEvents(hasItem("Bar(a) - STARTED"));
            listener.assertEvents(hasItem("Bar(b) - STARTING"));
            listener.assertEvents(hasItem("Bar(b) - STARTED"));
        }
        finally
        {
            barb.stop();
            bara.stop();
            foo.stop();
        }
    }
