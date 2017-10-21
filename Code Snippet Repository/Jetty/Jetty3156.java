    @Override
    public String renewSessionId (String oldClusterId, String oldNodeId, HttpServletRequest request)
    { 
        //generate a new id
        String newClusterId = newSessionId(request.hashCode());
        
        //TODO how to handle request for old id whilst id change is happening?
        
        //tell all contexts to update the id 
        for (SessionHandler manager:getSessionHandlers())
        {
            manager.renewSessionId(oldClusterId, oldNodeId, newClusterId, getExtendedId(newClusterId, request));
        }
        
        return newClusterId;
    }
