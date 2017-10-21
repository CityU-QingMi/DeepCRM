    public void loadBlacklistFromFile(String blacklistFilePath) {
        
        InputStream txtStream;
        try {
            String path = blacklistFilePath;
            if (path == null) {
                String uploadDir = WebloggerConfig.getProperty("uploads.dir");
                path = uploadDir + File.separator + BLACKLIST_FILE;
            }
            File blacklistFile = new File(path);
            
            // check our lastModified date to see if we need to re-read the file
            if (this.lastModified != null &&
                    this.lastModified.getTime() >= blacklistFile.lastModified()) {               
                mLogger.debug("Blacklist is current, no need to load again");
                return;
            } else {
                this.lastModified = new Date(blacklistFile.lastModified());
            }           
            txtStream = new FileInputStream(blacklistFile);           
            mLogger.info("Loading blacklist from "+path);
            
        } catch (Exception e) {
            // Roller keeps a copy in the webapp just in case
            txtStream = getClass().getResourceAsStream("/blacklist.txt");           
            mLogger.warn(
                "Couldn't find downloaded blacklist, loaded blacklist.txt from classpath instead");
        }
        
        if (txtStream != null) {
            readFromStream(txtStream, false);
        } else {
            mLogger.error("Couldn't load a blacklist file from anywhere, "
                        + "this means blacklist checking is disabled for now.");
        }
        mLogger.info("Number of blacklist string rules: "+blacklistStr.size());
        mLogger.info("Number of blacklist regex rules: "+blacklistRegex.size());
    }
