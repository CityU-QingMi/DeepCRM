    private void scanFile (File f, Map<String,TimeNSize> scanInfoMap, int depth)
    {
        try
        {
            if (!f.exists())
                return;

            if (f.isFile() || depth>0&& _reportDirs && f.isDirectory())
            {
                if ((_filter == null) || ((_filter != null) && _filter.accept(f.getParentFile(), f.getName())))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("scan accepted {}",f);
                    String name = f.getCanonicalPath();
                    scanInfoMap.put(name, new TimeNSize(f.lastModified(),f.isDirectory()?0:f.length()));
                }
                else
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("scan rejected {}",f);
                }
            }
            
            // If it is a directory, scan if it is a known directory or the depth is OK.
            if (f.isDirectory() && (depth<_scanDepth || _scanDepth==-1 || _scanDirs.contains(f)))
            {
                File[] files = f.listFiles();
                if (files != null)
                {
                    for (int i=0;i<files.length;i++)
                        scanFile(files[i], scanInfoMap,depth+1);
                }
                else
                    LOG.warn("Error listing files in directory {}", f);
            }
        }
        catch (IOException e)
        {
            LOG.warn("Error scanning watched files", e);
        }
    }
