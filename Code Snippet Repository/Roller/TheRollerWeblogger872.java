    public Entry postEntry(AtomRequest areq, Entry entry) throws AtomException {
        log.debug("Entering");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        try {
            // authenticated client posted a weblog entry
            String handle = pathInfo[0];
            Weblog website = 
                roller.getWeblogManager().getWeblogByHandle(handle);
            if (website == null) {
                throw new AtomNotFoundException("Cannot find weblog: " + handle);
            }
            if (!RollerAtomHandler.canEdit(user, website)) {
                throw new AtomNotAuthorizedException("Not authorized to access website: " + handle);
            }
            
            RollerAtomHandler.oneSecondThrottle();
            
            // Save it and commit it
            WeblogEntryManager mgr = roller.getWeblogEntryManager();
            WeblogEntry rollerEntry = new WeblogEntry();
            rollerEntry.setWebsite(website);
            rollerEntry.setCreatorUserName(this.user.getUserName());
            rollerEntry.setLocale(website.getLocale());
            copyToRollerEntry(entry, rollerEntry);
            mgr.saveWeblogEntry(rollerEntry);
            roller.flush();

            CacheManager.invalidate(website);
            if (rollerEntry.isPublished()) {
                roller.getIndexManager().addEntryReIndexOperation(rollerEntry);
            }

            rollerEntry = mgr.getWeblogEntry(rollerEntry.getId());
            Entry newEntry = createAtomEntry(rollerEntry);
            for (Object objLink : newEntry.getOtherLinks()) {
                Link link = (Link) objLink;
                if ("edit".equals(link.getRel())) {
                    log.debug("Exiting");
                    return createAtomEntry(rollerEntry);
                }
            }
            log.error("ERROR: no edit link found in saved media entry");
            log.debug("Exiting via exception");

        } catch (WebloggerException re) {
            throw new AtomException("Posting entry", re);
        }
        throw new AtomException("Posting entry");
    }
