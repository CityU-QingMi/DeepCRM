    private IPBanList() {
        
        log.debug("INIT");
        
        // load up set of denied ips
        String banIpsFilePath = WebloggerConfig.getProperty("ipbanlist.file");
        if(banIpsFilePath != null) {
            ModifiedFile banIpsFile = new ModifiedFile(banIpsFilePath);
            
            if(banIpsFile.exists() && banIpsFile.canRead()) {
                this.bannedIpsFile = banIpsFile;
                this.loadBannedIps();
            }
        }
    }
