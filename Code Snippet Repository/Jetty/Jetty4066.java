    private void createFile(File file, String str) throws IOException
    {
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(file);
            out.write(str.getBytes(StandardCharsets.UTF_8));
            out.flush();
        }
        finally
        {
            IO.close(out);
        }
    }
