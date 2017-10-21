    private BalancerMember selectBalancerMember(HttpServletRequest request)
    {
        if (_stickySessions)
        {
            String name = getBalancerMemberNameFromSessionId(request);
            if (name != null)
            {
                BalancerMember balancerMember = findBalancerMemberByName(name);
                if (balancerMember != null)
                    return balancerMember;
            }
        }
        int index = (int)(counter.getAndIncrement() % _balancerMembers.size());
        return _balancerMembers.get(index);
    }
