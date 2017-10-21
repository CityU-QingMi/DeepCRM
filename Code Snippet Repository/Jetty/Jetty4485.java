    protected void download(URI uri, Path destination) throws IOException
    {
        if (FS.ensureDirectoryExists(destination.getParent()))
            StartLog.log("MKDIR",_basehome.toShortForm(destination.getParent()));
        
        StartLog.log("DOWNLD","%s to %s",uri,_basehome.toShortForm(destination));

        HttpURLConnection http = (HttpURLConnection)uri.toURL().openConnection();
        http.setInstanceFollowRedirects(true);
        http.setAllowUserInteraction(false);
        
        int status = http.getResponseCode();
        
        if(status != HttpURLConnection.HTTP_OK)
        {
            throw new IOException("URL GET Failure [" + status + "/" + http.getResponseMessage() + "] on " + uri);
        }

        byte[] buf = new byte[8192];
        try (InputStream in = http.getInputStream(); OutputStream out = Files.newOutputStream(destination,StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE))
        {
            while (true)
            {
                int len = in.read(buf);

                if (len > 0)
                {
                    out.write(buf,0,len);
                }
                if (len < 0)
                {
                    break;
                }
            }
        }
    }
