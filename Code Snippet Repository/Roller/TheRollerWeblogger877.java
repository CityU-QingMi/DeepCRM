    public Entry getEntry(AtomRequest areq) throws AtomException {
        try {
            String[] pathInfo = Utilities.stringToStringArray(areq.getPathInfo(), "/");

            String filePath = filePathFromPathInfo(pathInfo);
            filePath = filePath.substring(0, filePath.length() - ".media-link".length());
            String handle = pathInfo[0];
            Weblog website = roller.getWeblogManager().getWeblogByHandle(handle);

            MediaFileManager fileMgr = roller.getMediaFileManager();
            MediaFile mf = fileMgr.getMediaFileByPath(website, filePath);

            log.debug("Exiting");
            if (mf != null) {
                return createAtomResourceEntry(website, mf);
            }
            
        } catch (WebloggerException ex) {
            throw new AtomException("ERROR fetching entry",ex);
        }
        throw new AtomNotFoundException("ERROR resource not found");
    }
