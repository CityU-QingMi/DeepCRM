        @Override
        public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
        {
            final Request baseRequest=Request.getBaseRequest(request);

            // pass to next filter
            if (_filterHolder!=null)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("call filter {}", _filterHolder);
                Filter filter= _filterHolder.getFilter();
                
                //if the request already does not support async, then the setting for the filter
                //is irrelevant. However if the request supports async but this filter does not
                //temporarily turn it off for the execution of the filter
                if (baseRequest.isAsyncSupported() && !_filterHolder.isAsyncSupported())
                { 
                    try
                    {
                        baseRequest.setAsyncSupported(false,_filterHolder.toString());
                        filter.doFilter(request, response, _next);
                    }
                    finally
                    {
                        baseRequest.setAsyncSupported(true,null);
                    }
                }
                else
                    filter.doFilter(request, response, _next);

                return;
            }

            // Call servlet
            HttpServletRequest srequest = (HttpServletRequest)request;
            if (_servletHolder == null)
                notFound(baseRequest, srequest, (HttpServletResponse)response);
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("call servlet " + _servletHolder);
                _servletHolder.handle(baseRequest,request, response);
            }
        }
