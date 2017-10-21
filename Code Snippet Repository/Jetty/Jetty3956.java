    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (_statsHandler == null)
        {
            LOG.warn("Statistics Handler not installed!");
            resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }
        if (_restrictToLocalhost)
        {
            if (!isLoopbackAddress(req.getRemoteAddr()))
            {
                resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                return;
            }
        }

        if (Boolean.valueOf( req.getParameter("statsReset")))
        {
            _statsHandler.statsReset();
            return;
        }

        String wantXml = req.getParameter("xml");
        if (wantXml == null)
          wantXml = req.getParameter("XML");

        if (Boolean.valueOf(wantXml))
        {
            sendXmlResponse(resp);
        }
        else
        {
            sendTextResponse(resp);
        }
    }
