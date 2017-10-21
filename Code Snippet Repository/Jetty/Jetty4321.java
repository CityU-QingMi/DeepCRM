    public File prepareServerFile(String filename, int filesize) throws IOException
    {
        File dir = testdir.getPath().toFile();
        File testFile = new File(dir,filename);
        // Make sure we have a uniq filename (to work around windows File.delete bug)
        int i = 0;
        while (testFile.exists())
        {
            testFile = new File(dir,(i++) + "-" + filename);
        }

        FileOutputStream fos = null;
        ByteArrayInputStream in = null;
        try
        {
            fos = new FileOutputStream(testFile,false);
            in = new ByteArrayInputStream(generateContent(filesize).getBytes(encoding));
            IO.copy(in,fos);
            return testFile;
        }
        finally
        {
            IO.close(in);
            IO.close(fos);
        }
    }
