    public void deleteEntry(AtomRequest areq) throws AtomException {
        log.debug("Entering");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length > 2) {
            // URI is /blogname/entry/entryid
            if (pathInfo[1].equals("entry")) {
                EntryCollection ecol = new EntryCollection(user, atomURL);
                ecol.deleteEntry(areq);
                return;
            } else if (pathInfo[1].equals("resource")) {
                MediaCollection mcol = new MediaCollection(user, atomURL);
                mcol.deleteEntry(areq);
                return;
            }
        }
        throw new AtomNotFoundException("cannot find specified entry/resource");
    }
