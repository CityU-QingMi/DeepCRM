    public void addJars(Resource lib)
    {
        if (lib.exists() && lib.isDirectory())
        {
            String[] files=lib.list();
            if (files != null)
            {
                Arrays.sort(files);
            }
            for (int f=0;files!=null && f<files.length;f++)
            {
                try 
                {
                    Resource fn=lib.addPath(files[f]);
                    if(LOG.isDebugEnabled())
                        LOG.debug("addJar - {}", fn);
                    String fnlc=fn.getName().toLowerCase(Locale.ENGLISH);
                    // don't check if this is a directory, see Bug 353165
                    if (isFileSupported(fnlc))
                    {
                        String jar=fn.toString();
                        jar=StringUtil.replace(jar, ",", "%2C");
                        jar=StringUtil.replace(jar, ";", "%3B");
                        addClassPath(jar);
                    }
                }
                catch (Exception ex)
                {
                    LOG.warn(Log.EXCEPTION,ex);
                }
            }
        }
    }
