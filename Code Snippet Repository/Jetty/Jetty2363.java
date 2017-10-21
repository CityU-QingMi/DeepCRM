    private boolean isBackendLocation(URI locationURI)
    {
        for (BalancerMember balancerMember : _balancerMembers)
        {
            URI backendURI = balancerMember.getBackendURI();
            if (backendURI.getHost().equals(locationURI.getHost()) &&
                    backendURI.getScheme().equals(locationURI.getScheme())
                    && backendURI.getPort() == locationURI.getPort())
            {
                return true;
            }
        }
        return false;
    }
