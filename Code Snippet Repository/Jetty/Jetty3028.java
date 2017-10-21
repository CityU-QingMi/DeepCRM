    public boolean isProtectedTarget(String target)
    {
        if (target == null || _protectedTargets == null)
            return false;

        while (target.startsWith("//"))
            target=URIUtil.compactPath(target);

        for (int i=0; i<_protectedTargets.length; i++)
        {
            String t=_protectedTargets[i];
            if (StringUtil.startsWithIgnoreCase(target,t))
            {
                if (target.length()==t.length())
                    return true;

                // Check that the target prefix really is a path segment, thus
                // it can end with /, a query, a target or a parameter
                char c=target.charAt(t.length());
                if (c=='/'||c=='?'||c=='#'||c==';')
                    return true;
            }
        }
        return false;
    }
