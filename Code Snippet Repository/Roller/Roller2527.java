    public Entry getEntry(AtomRequest areq) throws AtomException {
        try {
            String entryid = Utilities.stringToStringArray(areq.getPathInfo(),"/")[2];
            WeblogEntry entry = roller.getWeblogEntryManager().getWeblogEntry(entryid);
            if (entry == null) {
                throw new AtomNotFoundException("Cannot find specified entry/resource");
            }
            if (!RollerAtomHandler.canView(user, entry)) {
                throw new AtomNotAuthorizedException("Not authorized to view entry");
            } else {
                return createAtomEntry(entry);
            }
        } catch (WebloggerException ex) {
            throw new AtomException("ERROR fetching entry", ex);
        }
    }
