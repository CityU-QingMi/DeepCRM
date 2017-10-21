    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(this.getClass().getName());
        s.append("[");
        s.append("users.count=").append(this.getKnownUserIdentities().size());
        s.append("identityService=").append(this.getIdentityService());
        s.append("]");
        return s.toString();
    }
