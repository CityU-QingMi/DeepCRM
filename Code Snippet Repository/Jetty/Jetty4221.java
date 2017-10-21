    private void handle(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        String origin = request.getHeader(ORIGIN_HEADER);
        // Is it a cross origin request ?
        if (origin != null && isEnabled(request))
        {
            if (anyOriginAllowed || originMatches(allowedOrigins, origin))
            {
                if (isSimpleRequest(request))
                {
                    LOG.debug("Cross-origin request to {} is a simple cross-origin request", request.getRequestURI());
                    handleSimpleResponse(request, response, origin);
                }
                else if (isPreflightRequest(request))
                {
                    LOG.debug("Cross-origin request to {} is a preflight cross-origin request", request.getRequestURI());
                    handlePreflightResponse(request, response, origin);
                    if (chainPreflight)
                        LOG.debug("Preflight cross-origin request to {} forwarded to application", request.getRequestURI());
                    else
                        return;
                }
                else
                {
                    LOG.debug("Cross-origin request to {} is a non-simple cross-origin request", request.getRequestURI());
                    handleSimpleResponse(request, response, origin);
                }

                if (anyTimingOriginAllowed || originMatches(allowedTimingOrigins, origin))
                {
                    response.setHeader(TIMING_ALLOW_ORIGIN_HEADER, origin);
                }
                else
                {
                    LOG.debug("Cross-origin request to " + request.getRequestURI() + " with origin " + origin + " does not match allowed timing origins " + allowedTimingOrigins);
                }
            }
            else
            {
                LOG.debug("Cross-origin request to " + request.getRequestURI() + " with origin " + origin + " does not match allowed origins " + allowedOrigins);
            }
        }

        chain.doFilter(request, response);
    }
