    @Override
    public String newSessionId(HttpServletRequest request, long created)
    {
        if (request==null)
            return newSessionId(created);

        // A requested session ID can only be used if it is in use already.
        String requested_id=request.getRequestedSessionId();
        if (requested_id!=null)
        {
            String cluster_id=getId(requested_id);
            if (isIdInUse(cluster_id))
                return cluster_id;
        }


        // Else reuse any new session ID already defined for this request.
        String new_id=(String)request.getAttribute(__NEW_SESSION_ID);
        if (new_id!=null&&isIdInUse(new_id))
            return new_id;

        // pick a new unique ID!
        String id = newSessionId(request.hashCode());

        request.setAttribute(__NEW_SESSION_ID,id);
        return id;
    }
