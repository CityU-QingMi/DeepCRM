    public AtomMediaResource getMediaResource(AtomRequest areq) throws AtomException {
        log.debug("Entering");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        try {
            // authenticated client posted a weblog entry
            String handle = pathInfo[0];
            MediaFileManager fmgr = roller.getMediaFileManager();
            Weblog website = WebloggerFactory.getWeblogger().getWeblogManager().getWeblogByHandle(handle);
            if (!RollerAtomHandler.canEdit(user, website)) {
                throw new AtomNotAuthorizedException("Not authorized to edit weblog: " + handle);
            }
            if (pathInfo.length > 1) {
                try {                                        
                    // Parse pathinfo to determine file path
                    String filePath = filePathFromPathInfo(pathInfo);
                    MediaFile mf = fmgr.getMediaFileByOriginalPath(website, filePath);
                    return new AtomMediaResource(
                            mf.getName(),
                            mf.getLength(),
                            new Date(mf.getLastModified()),
                            mf.getInputStream());
                } catch (Exception e) {
                    throw new AtomException(
                        "Unexpected error during file upload", e);
                }
            }
            throw new AtomException("Incorrect path information");
        
        } catch (WebloggerException re) {
            throw new AtomException("Posting media");
        }
    }
