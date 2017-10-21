    static void delete(File dir, FileFilter filter)
    {
        File[] files = dir.listFiles(filter);
        if (files != null)
        {
            for(File f: files)
            {
                if(f.isDirectory())
                    delete(f, filter);
                else
                    f.delete();
            }
        }
    }
