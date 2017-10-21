    protected byte[] loadContentFileBytes(final String fileName) throws IOException
    {
        String relPath = fileName;
        relPath = relPath.replaceFirst("^/context/","");
        relPath = relPath.replaceFirst("^/","");

        File contentFile =  getTestFile(relPath);

        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try
        {
            in = new FileInputStream(contentFile);
            out = new ByteArrayOutputStream();
            IO.copy(in,out);
            return out.toByteArray();
        }
        finally
        {
            IO.close(out);
            IO.close(in);
        }
    }
