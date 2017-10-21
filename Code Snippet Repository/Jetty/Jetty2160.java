    private List<File> extractFiles(String propertyValue)
    {
        StringTokenizer tokenizer = new StringTokenizer(propertyValue, ",;", false);
        List<File> files = new ArrayList<File>();
        while (tokenizer.hasMoreTokens())
        {
            String tok = tokenizer.nextToken();
            try
            {
                URL url = new URL(tok);
                url = BundleFileLocatorHelperFactory.getFactory().getHelper().getFileURL(url);
                if (url.getProtocol().equals("file"))
                {
                    Resource res = Resource.newResource(url);
                    File folder = res.getFile();
                    if (folder != null)
                    {
                        files.add(folder);
                    }
                }
            }
            catch (Throwable mfe)
            {
                LOG.warn(mfe);
            }
        }
        return files;
    }
