    private boolean areHeadersAllowed(List<String> requestedHeaders)
    {
        if (anyHeadersAllowed)
        {
            LOG.debug("Any header is allowed");
            return true;
        }

        boolean result = true;
        for (String requestedHeader : requestedHeaders)
        {
            boolean headerAllowed = false;
            for (String allowedHeader : allowedHeaders)
            {
                if (requestedHeader.equalsIgnoreCase(allowedHeader.trim()))
                {
                    headerAllowed = true;
                    break;
                }
            }
            if (!headerAllowed)
            {
                result = false;
                break;
            }
        }
        LOG.debug("Headers [{}] are" + (result ? "" : " not") + " among allowed headers {}", requestedHeaders, allowedHeaders);
        return result;
    }
