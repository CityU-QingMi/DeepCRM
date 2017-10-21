    private static URI checkFileAlias(final URI uri, final File file)
    {
        try
        {
            if (!URIUtil.equalsIgnoreEncodings(uri,file.toURI()))
            {
                // Return alias pointing to Java File normalized URI
                return new File(uri).getAbsoluteFile().toURI();
            }

            String abs=file.getAbsolutePath();
            String can=file.getCanonicalPath();

            if (!abs.equals(can))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("ALIAS abs={} can={}",abs,can);

                URI alias=new File(can).toURI();
                // Have to encode the path as File.toURI does not!
                return new URI("file://"+URIUtil.encodePath(alias.getPath()));
            }
        }
        catch(Exception e)
        {
            LOG.warn("bad alias for {}: {}",file,e.toString());
            LOG.debug(e);
            try
            {
                return new URI("http://eclipse.org/bad/canonical/alias");
            }
            catch(Exception e2)
            {
                LOG.ignore(e2);
                throw new RuntimeException(e);
            }
        }

        return null;
    }
