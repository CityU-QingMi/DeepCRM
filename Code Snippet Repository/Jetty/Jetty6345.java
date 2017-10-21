    private void assertFileUpload(File file, File sha1File) throws IOException, NoSuchAlgorithmException
    {
        Assert.assertThat("Path should exist: " + file,file.exists(),is(true));
        Assert.assertThat("Path should not be a directory:" + file,file.isDirectory(),is(false));

        String expectedSha1 = Sha1Sum.loadSha1(sha1File);
        String actualSha1 = Sha1Sum.calculate(file);

        Assert.assertThat("SHA1Sum of content: " + file,expectedSha1,equalToIgnoringCase(actualSha1));
    }
