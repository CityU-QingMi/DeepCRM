    public void saveFileContent(Weblog weblog, String fileId, InputStream is)
            throws FileNotFoundException, FilePathException, FileIOException {

        // make sure uploads area exists for this weblog
        File dirPath = this.getRealFile(weblog, null);

        // create File that we are about to save
        File saveFile = new File(dirPath.getAbsolutePath() + File.separator
                + fileId);

        byte[] buffer = new byte[RollerConstants.EIGHT_KB_IN_BYTES];
        int bytesRead;
        OutputStream bos = null;
        try {
            bos = new FileOutputStream(saveFile);
            while ((bytesRead = is.read(buffer, 0,
                    RollerConstants.EIGHT_KB_IN_BYTES)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            log.debug("The file has been written to ["
                    + saveFile.getAbsolutePath() + "]");
        } catch (Exception e) {
            throw new FileIOException("ERROR uploading file", e);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (Exception ignored) {
            }
        }

    }
