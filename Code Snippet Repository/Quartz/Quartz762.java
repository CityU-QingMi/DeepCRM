    public void testUnimplementedMethods() throws Exception {
        ThreadPool tp = makeIncompleteThreadPool();
        try {
            tp.setInstanceName("name");
            fail();
        } catch (AbstractMethodError ame) {
            // expected
        }

        SchedulerDetailsSetter.setDetails(tp, "name", "id");
    }
