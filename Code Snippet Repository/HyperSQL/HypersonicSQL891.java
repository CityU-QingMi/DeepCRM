    BlobData createBlobFromFile(String filename) {

        File        file       = getFile(filename);
        long        fileLength = file.length();
        InputStream is         = null;

        try {
            BlobData blob = session.createBlob(fileLength);

            is = new FileInputStream(file);

            database.lobManager.setBytesForNewBlob(blob.getId(), is,
                                                   fileLength);

            return blob;
        } catch (IOException e) {
            throw Error.error(ErrorCode.FILE_IO_ERROR);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {}
        }
    }
