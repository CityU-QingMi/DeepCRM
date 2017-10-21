    private String extractBalancerMemberNameFromSessionId(String sessionId)
    {
        int idx = sessionId.lastIndexOf(".");
        if (idx > 0)
        {
            String sessionIdSuffix = sessionId.substring(idx + 1);
            return sessionIdSuffix.length() > 0 ? sessionIdSuffix : null;
        }
        return null;
    }
