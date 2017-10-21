    public void testSuccessUploadOfATextFileMultipartRequest() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCharacterEncoding("text/html");
        req.setMethod("post");
        req.addHeader("Content-type", "multipart/form-data; boundary=---1234");

        // inspired by the unit tests for jakarta commons fileupload
        String content = ("-----1234\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"deleteme.txt\"\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "Unit test of FileUploadInterceptor" +
                "\r\n" +
                "-----1234--\r\n");
        req.setContent(content.getBytes("US-ASCII"));

        MyFileupAction action = new MyFileupAction();

        MockActionInvocation mai = new MockActionInvocation();
        mai.setAction(action);
        mai.setResultCode("success");
        mai.setInvocationContext(ActionContext.getContext());
        Map<String, Object> param = new HashMap<>();
        ActionContext.getContext().setParameters(HttpParameters.create(param).build());
        ActionContext.getContext().put(ServletActionContext.HTTP_REQUEST, createMultipartRequest(req, 2000));

        interceptor.intercept(mai);

        assertTrue(!action.hasErrors());

        HttpParameters parameters = mai.getInvocationContext().getParameters();
        assertTrue(parameters.keySet().size() == 3);
        UploadedFile[] files = (UploadedFile[]) parameters.get("file").getObject();
        String[] fileContentTypes = parameters.get("fileContentType").getMultipleValues();
        String[] fileRealFilenames = parameters.get("fileFileName").getMultipleValues();

        assertNotNull(files);
        assertNotNull(fileContentTypes);
        assertNotNull(fileRealFilenames);
        assertTrue(files.length == 1);
        assertTrue(fileContentTypes.length == 1);
        assertTrue(fileRealFilenames.length == 1);
        assertEquals("text/html", fileContentTypes[0]);
        assertNotNull("deleteme.txt", fileRealFilenames[0]);
    }
