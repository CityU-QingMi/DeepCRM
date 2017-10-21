    @Test
    public void testFileUpload() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // One liner to upload files
        Response response = client.newRequest("localhost", 8080).file(Paths.get("file_to_upload.txt")).send();

        Assert.assertEquals(200, response.getStatus());
    }
