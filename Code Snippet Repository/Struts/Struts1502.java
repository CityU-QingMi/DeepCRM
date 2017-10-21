    public void testProfileCallback() throws Exception {

        MockProfilingBlock<String> block = new MockProfilingBlock<String>() {
            @Override
            public String performProfiling() throws Exception {
                Thread.sleep(1050);
                return "OK";
            }
        };
        String result = UtilTimerStack.profile("p1", block);
        assertEquals(result, "OK");
        assertNotNull(block.getProfilingTimerBean());
        assertTrue(block.getProfilingTimerBean().totalTime >= 1000);

    }
