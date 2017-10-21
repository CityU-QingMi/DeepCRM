    public void deleteEntry(AtomRequest areq) throws AtomException {
        try {
            String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
            String handle = pathInfo[0];
            Weblog website = roller.getWeblogManager().getWeblogByHandle(handle);
            if (website == null) {
                throw new AtomNotFoundException("cannot find specified weblog");
            }
            if (RollerAtomHandler.canEdit(user, website) && pathInfo.length > 1) {
                try {
                    String path = filePathFromPathInfo(pathInfo);
                    String fileName = path.substring(0, path.length() - ".media-link".length());
                    MediaFileManager fmgr = roller.getMediaFileManager();
                    MediaFile mf = fmgr.getMediaFileByPath(website, path);
                    fmgr.removeMediaFile(website, mf);
                    log.debug("Deleted media entry: " + fileName);
                    return;
                    
                } catch (Exception e) {
                    String msg = "ERROR deleting media entry";
                    log.error(msg, e);
                    throw new AtomException(msg);
                }
            }
            log.debug("Not authorized to delete media entry"); 
            log.debug("Exiting via exception"); 

        } catch (WebloggerException ex) {
            throw new AtomException("ERROR deleting media entry",ex);
        }
        throw new AtomNotAuthorizedException("Not authorized to delete entry");
    }
