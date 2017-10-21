    public boolean checkContext(final String target, final Request baseRequest, final HttpServletResponse response) throws IOException
    {
        DispatcherType dispatch = baseRequest.getDispatcherType();

        // Check the vhosts
        if (!checkVirtualHost(baseRequest))
            return false;

        if (!checkContextPath(target))
            return false;

        // Are we not the root context?
        // redirect null path infos
        if (!_allowNullPathInfo && _contextPath.length() == target.length() && _contextPath.length()>1)
        {
            // context request must end with /
            baseRequest.setHandled(true);
            if (baseRequest.getQueryString() != null)
                response.sendRedirect(baseRequest.getRequestURI() + "/?" + baseRequest.getQueryString());
            else
                response.sendRedirect(baseRequest.getRequestURI() + "/");
            return false;
        }

        switch (_availability)
        {
            case SHUTDOWN:
            case UNAVAILABLE:
                baseRequest.setHandled(true);
                response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                return false;
            default:
                if ((DispatcherType.REQUEST.equals(dispatch) && baseRequest.isHandled()))
                    return false;
        }

        return true;
    }
