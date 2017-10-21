    @Test
    public void unknownContentLength() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getContentType()).thenReturn("multipart/form-data; charset=utf-8; boundary=__X_BOUNDARY__");
        Mockito.when(request.getMethod()).thenReturn("POST");
        Mockito.when(request.getContentLength()).thenReturn(Integer.valueOf(-1));
        StringBuilder entity = new StringBuilder();
        entity.append("\r\n--__X_BOUNDARY__\r\n");
        entity.append("Content-Disposition: form-data; name=\"upload\"; filename=\"test.csv\"\r\n");
        entity.append("Content-Type: text/csv\r\n\r\n1,2\r\n\r\n");
        entity.append("--__X_BOUNDARY__\r\n");
        entity.append("Content-Disposition: form-data; name=\"upload2\"; filename=\"test2.csv\"\r\n");
        entity.append("Content-Type: text/csv\r\n\r\n3,4\r\n\r\n");
        entity.append("--__X_BOUNDARY__--\r\n");
        Mockito.when(request.getInputStream()).thenReturn(new DelegatingServletInputStream(new ByteArrayInputStream(entity.toString().getBytes(StandardCharsets.UTF_8))));
        multiPart.setMaxSize("4");
        multiPart.parse(request, tempDir.toString());
        LocalizedMessage next = multiPart.getErrors().iterator().next();
        Assert.assertEquals(next.getTextKey(), "struts.messages.upload.error.SizeLimitExceededException");
    }
