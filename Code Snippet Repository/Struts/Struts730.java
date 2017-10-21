    protected boolean streamFileToDisk(FileItemStream itemStream, File file) throws IOException {
        boolean result = false;
        try (InputStream input = itemStream.openStream();
                OutputStream output = new BufferedOutputStream(new FileOutputStream(file), bufferSize)) {
            byte[] buffer = new byte[bufferSize];
            LOG.debug("Streaming file using buffer size {}.", bufferSize);
            for (int length = 0; ((length = input.read(buffer)) > 0); ) {
                output.write(buffer, 0, length);
            }
            result = true;
        }
        return result;
    }
