    protected List<Resource> findWebInfLibJars(WebAppContext context)
    throws Exception
    {
        Resource web_inf = context.getWebInf();
        if (web_inf==null || !web_inf.exists())
            return null;

        List<Resource> jarResources = new ArrayList<Resource>();
        Resource web_inf_lib = web_inf.addPath("/lib");
        if (web_inf_lib.exists() && web_inf_lib.isDirectory())
        {
            String[] files=web_inf_lib.list();
            if (files != null)
            {
                Arrays.sort(files);
            }
            for (int f=0;files!=null && f<files.length;f++)
            {
                try
                {
                    Resource file = web_inf_lib.addPath(files[f]);
                    String fnlc = file.getName().toLowerCase(Locale.ENGLISH);
                    int dot = fnlc.lastIndexOf('.');
                    String extension = (dot < 0 ? null : fnlc.substring(dot));
                    if (extension != null && (extension.equals(".jar") || extension.equals(".zip")))
                    {
                        jarResources.add(file);
                    }
                }
                catch (Exception ex)
                {
                    LOG.warn(Log.EXCEPTION,ex);
                }
            }
        }
        return jarResources;
    }
