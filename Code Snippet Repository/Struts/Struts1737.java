    @Test
    public void convertUploadedFileToFile() throws Exception {
        // given
        UploadedFileConverter ufc = new UploadedFileConverter();
        UploadedFile uploadedFile = new StrutsUploadedFile(tempFile);

        // when
        Object result = ufc.convertValue(context, target, member, propertyName, uploadedFile, File.class);

        // then
        assertThat(result).isInstanceOf(File.class);
        File file = (File) result;
        assertThat(file.length()).isEqualTo(tempFile.length());
        assertThat(file.getAbsolutePath()).isEqualTo(tempFile.getAbsolutePath());
    }
