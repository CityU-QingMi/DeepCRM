    private void touch(File file)
    {
        try
        {
            IO.delete(file);
            try (OutputStream out = new FileOutputStream(file,false))
            {
                out.write("<h1>Hello</h1>".getBytes());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
