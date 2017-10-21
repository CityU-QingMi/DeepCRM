    @Override
    public String getNegotiatedSubprotocol()
    {
        String acceptedSubProtocol = getUpgradeResponse().getAcceptedSubProtocol();
        if (acceptedSubProtocol == null)
        {
            return "";
        }
        return acceptedSubProtocol;
    }
