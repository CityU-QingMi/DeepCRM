    public Fuzzer(Fuzzed testcase) throws Exception
    {
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();

        int bigMessageSize = 20 * MBYTE;

        policy.setMaxTextMessageSize(bigMessageSize);
        policy.setMaxBinaryMessageSize(bigMessageSize);
        policy.setIdleTimeout(5000);

        this.client = new BlockheadClient(policy,testcase.getServerURI());
        this.client.setTimeout(2,TimeUnit.SECONDS);
        this.generator = testcase.getLaxGenerator();
        this.testname = testcase.getTestMethodName();
    }
