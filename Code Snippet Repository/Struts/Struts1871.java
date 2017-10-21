    protected void setUp() throws Exception {
        super.setUp();
        action = new TestAction();
        container.inject(action);

        interceptor = new FileUploadInterceptor();
        container.inject(interceptor);
        tempDir = File.createTempFile("struts", "fileupload");
        tempDir.delete();
        tempDir.mkdirs();
    }
