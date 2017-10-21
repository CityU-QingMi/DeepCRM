    @Override
    public synchronized String[] list()
    {
        if (isDirectory() && _list==null)
        {
            List<String> list = null;
            try
            {
                list = listEntries();
            }
            catch (Exception e)
            {
                //Sun's JarURLConnection impl for jar: protocol will close a JarFile in its connect() method if
                //useCaches == false (eg someone called URLConnection with defaultUseCaches==true).
                //As their sun.net.www.protocol.jar package caches JarFiles and/or connections, we can wind up in 
                //the situation where the JarFile we have remembered in our _jarFile member has actually been closed
                //by other code.
                //So, do one retry to drop a connection and get a fresh JarFile
                LOG.warn("Retrying list:"+e);
                LOG.debug(e);
                close();
                list = listEntries();
            }

            if (list != null)
            {
                _list=new String[list.size()];
                list.toArray(_list);
            }  
        }
        return _list;
    }
