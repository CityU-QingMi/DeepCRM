    public Feed getCollection(AtomRequest areq) throws AtomException {
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        
        if (pathInfo.length > 0 && pathInfo[1].equals("entries")) {
            EntryCollection ecol = new EntryCollection(user, atomURL);
            return ecol.getCollection(areq);
            
        } else if (pathInfo.length > 0 && pathInfo[1].equals("resources")) {
            MediaCollection mcol = new MediaCollection(user, atomURL);
            return mcol.getCollection(areq);
        }
        throw new AtomNotFoundException("Cannot find collection specified");
    }
