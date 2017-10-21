    private void initBalancers() throws ServletException
    {
        Set<BalancerMember> members = new HashSet<>();
        for (String balancerName : getBalancerNames())
        {
            String memberProxyToParam = BALANCER_MEMBER_PREFIX + balancerName + ".proxyTo";
            String proxyTo = getServletConfig().getInitParameter(memberProxyToParam);
            if (proxyTo == null || proxyTo.trim().length() == 0)
                throw new UnavailableException(memberProxyToParam + " parameter is empty.");
            members.add(new BalancerMember(balancerName, proxyTo));
        }
        _balancerMembers.addAll(members);
    }
