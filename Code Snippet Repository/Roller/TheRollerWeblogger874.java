    public void putEntry(AtomRequest areq, Entry entry) throws AtomException {
        log.debug("Entering");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        try {
            if (pathInfo.length == 3)
            {
                // URI is /blogname/entries/entryid
                WeblogEntry rollerEntry =
                    roller.getWeblogEntryManager().getWeblogEntry(pathInfo[2]);
                if (rollerEntry == null) {
                    throw new AtomNotFoundException(
                        "Cannot find specified entry/resource");  
                }
                if (RollerAtomHandler.canEdit(user, rollerEntry)) {
            
                    RollerAtomHandler.oneSecondThrottle();
                    
                    WeblogEntryManager mgr = roller.getWeblogEntryManager();
                    copyToRollerEntry(entry, rollerEntry);
                    rollerEntry.setUpdateTime(new Timestamp(new Date().getTime()));
                    mgr.saveWeblogEntry(rollerEntry);
                    roller.flush();
                    
                    CacheManager.invalidate(rollerEntry.getWebsite());
                    if (rollerEntry.isPublished()) {
                        roller.getIndexManager().addEntryReIndexOperation(rollerEntry);
                    }
                    log.debug("Exiting");
                    return;
                }
                throw new AtomNotAuthorizedException("ERROR not authorized to update entry");
            }
            throw new AtomNotFoundException("Cannot find specified entry/resource");
            
        } catch (WebloggerException re) {
            throw new AtomException("Updating entry");
        }
    }
