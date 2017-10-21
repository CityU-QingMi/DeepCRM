    public void deleteEntry(AtomRequest areq) throws AtomException {
        try {
            String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
            WeblogEntry rollerEntry = roller.getWeblogEntryManager().getWeblogEntry(pathInfo[2]);
            if (rollerEntry == null) {
                throw new AtomNotFoundException("cannot find specified entry/resource");
            }
            if (RollerAtomHandler.canEdit(user, rollerEntry)) {
                WeblogEntryManager mgr = roller.getWeblogEntryManager();
                CacheManager.invalidate(rollerEntry.getWebsite());
                reindexEntry(rollerEntry);
                mgr.removeWeblogEntry(rollerEntry);
                log.debug("Deleted entry:" + rollerEntry.getAnchor());
                roller.flush();
                return;
            }
            log.debug("Not authorized to delete entry"); 
            log.debug("Exiting via exception"); 
            
        } catch (WebloggerException ex) {
            throw new AtomException("ERROR deleting entry",ex);
        }
        throw new AtomNotAuthorizedException("Not authorized to delete entry");
    }
