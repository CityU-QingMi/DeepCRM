    protected InputSource resolveEntity(String pid, String sid)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("resolveEntity(" + pid + ", " + sid + ")");

        if (sid!=null && sid.endsWith(".dtd"))
            _dtd=sid;

        URL entity = null;
        if (pid != null)
            entity = (URL) _redirectMap.get(pid);
        if (entity == null)
            entity = (URL) _redirectMap.get(sid);
        if (entity == null)
        {
            String dtd = sid;
            if (dtd.lastIndexOf('/') >= 0)
                dtd = dtd.substring(dtd.lastIndexOf('/') + 1);

            if (LOG.isDebugEnabled())
                LOG.debug("Can't exact match entity in redirect map, trying " + dtd);
            entity = (URL) _redirectMap.get(dtd);
        }

        if (entity != null)
        {
            try
            {
                InputStream in = entity.openStream();
                if (LOG.isDebugEnabled())
                    LOG.debug("Redirected entity " + sid + " --> " + entity);
                InputSource is = new InputSource(in);
                is.setSystemId(sid);
                return is;
            }
            catch (IOException e)
            {
                LOG.ignore(e);
            }
        }
        return null;
    }
