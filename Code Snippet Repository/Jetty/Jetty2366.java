    private Set<String> getBalancerNames() throws ServletException
    {
        Set<String> names = new HashSet<>();
        for (String initParameterName : Collections.list(getServletConfig().getInitParameterNames()))
        {
            if (!initParameterName.startsWith(BALANCER_MEMBER_PREFIX))
                continue;

            int endOfNameIndex = initParameterName.lastIndexOf(".");
            if (endOfNameIndex <= BALANCER_MEMBER_PREFIX.length())
                throw new UnavailableException(initParameterName + " parameter does not provide a balancer member name");

            names.add(initParameterName.substring(BALANCER_MEMBER_PREFIX.length(), endOfNameIndex));
        }
        return names;
    }
