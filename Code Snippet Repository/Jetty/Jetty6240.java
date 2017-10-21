    @Override
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested)
    {
        if ((requested == null) || (requested.size() == 0))
        {
            // nothing requested, don't return anything
            return NO_SUBPROTOCOL;
        }

        // Nothing specifically called out as being supported by the endpoint
        if ((supported == null) || (supported.isEmpty()))
        {
            // Just return the first hit in this case
            LOG.warn("Client requested Subprotocols on endpoint with none supported: {}",QuoteUtil.join(requested,","));
            return NO_SUBPROTOCOL;
        }

        // Return the first matching hit from the list of supported protocols.
        for (String possible : requested)
        {
            if (possible == null)
            {
                // skip null
                continue;
            }

            if (supported.contains(possible))
            {
                return possible;
            }
        }

        LOG.warn("Client requested subprotocols {} do not match any endpoint supported subprotocols {}",QuoteUtil.join(requested,","),
                QuoteUtil.join(supported,","));
        return NO_SUBPROTOCOL;
    }
