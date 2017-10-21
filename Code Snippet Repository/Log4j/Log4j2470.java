    @Test
    public void testQueuePositionalParametersAreAppendedToIfNonNull() {
        QueuePositionalParams params = new QueuePositionalParams();
        params.queue = new LinkedList<Integer>();
        params.queue.add(234);
        Queue<Integer> list = params.queue;
        new CommandLine(params).parse("3", "2", "1");
        assertSame(list, params.queue);
        assertEquals(new LinkedList(Arrays.asList(234, 3, 2, 1)), params.queue);
    }
