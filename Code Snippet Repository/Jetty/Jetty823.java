    public String getResponse(String path) throws IOException
    {
        URI destUri = getServerURI().resolve(path);
        URL url = destUri.toURL();

        URLConnection conn = url.openConnection();

        InputStream in = null;
        try
        {
            in = conn.getInputStream();
            return IO.toString(in);
        }
        finally
        {
            IO.close(in);
        }
    }
