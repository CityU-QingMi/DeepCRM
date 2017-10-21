    public FileResource(URL url)
            throws IOException, URISyntaxException
    {
        File file;
        try
        {
            // Try standard API to convert URL to file.
            file =new File(url.toURI());
            assertValidPath(file.toString());
        }
        catch (URISyntaxException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            if (!url.toString().startsWith("file:"))
                throw new IllegalArgumentException("!file:");
            
            LOG.ignore(e);
            try
            {
                // Assume that File.toURL produced unencoded chars. So try encoding them.
                String file_url="file:"+URIUtil.encodePath(url.toString().substring(5));
                URI uri = new URI(file_url);
                if (uri.getAuthority()==null)
                    file = new File(uri);
                else
                    file = new File("//"+uri.getAuthority()+URIUtil.decodePath(url.getFile()));
            }
            catch (Exception e2)
            {
                LOG.ignore(e2);
                // Still can't get the file.  Doh! try good old hack!
                URLConnection connection=url.openConnection();
                Permission perm = connection.getPermission();
                file = new File(perm==null?url.getFile():perm.getName());
            }
        }
        
        _file=file;
        _uri=normalizeURI(_file,url.toURI());
        _alias=checkFileAlias(_uri,_file);
    }
