    @Override
    public InputStream getInputStream() throws IOException
    {
/**/
/**/
/**/
/**/
        if (Files.isDirectory(path))
            throw new IOException(path + " is a directory");

        return Files.newInputStream(path,StandardOpenOption.READ);
    }
