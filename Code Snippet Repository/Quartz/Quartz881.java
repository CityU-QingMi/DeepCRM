    protected File[] getUpdatedOrNewFiles(String dirName, final long lastDate, final long maxAgeDate) {

        File dir = new File(dirName);
        if(!dir.exists() || !dir.isDirectory()) {
            return null;
        } 
        
        File[] files = dir.listFiles(new FileFilter() {

            public boolean accept(File pathname) {
                if(pathname.lastModified() > lastDate && pathname.lastModified() < maxAgeDate)
                    return true;
                return false;
            }});

        if(files == null)
            files = new File[0];
        
        return files;
    }
