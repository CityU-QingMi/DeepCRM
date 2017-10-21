    public Entry getEntry(AtomRequest areq) throws AtomException {
        log.debug("Entering");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        // URI is /blogname/entries/entryid
        if (pathInfo.length > 2) {
            if (pathInfo[1].equals("entry")) {
                EntryCollection ecol = new EntryCollection(user, atomURL);
                return ecol.getEntry(areq);
            } else if (pathInfo[1].equals("resource") && pathInfo[pathInfo.length - 1].endsWith(".media-link")) {
                MediaCollection mcol = new MediaCollection(user, atomURL);
                return mcol.getEntry(areq);                    
            }
        }
        throw new AtomNotFoundException("Cannot find specified entry/resource");
    }
