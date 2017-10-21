        @Override
        public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
        {
            if (LOG.isDebugEnabled())
                LOG.debug("doFilter " + _filter);

            // pass to next filter
            if (_filter < _chain.size())
            {
                FilterHolder holder= _chain.get(_filter++);
                if (LOG.isDebugEnabled())
                    LOG.debug("call filter " + holder);
                Filter filter= holder.getFilter();

                //if the request already does not support async, then the setting for the filter
                //is irrelevant. However if the request supports async but this filter does not
                //temporarily turn it off for the execution of the filter
                if (!holder.isAsyncSupported() && _baseRequest.isAsyncSupported())
                {
                    try
                    {
                        _baseRequest.setAsyncSupported(false,holder.toString());
                        filter.doFilter(request, response, this);
                    }
                    finally
                    {
                        _baseRequest.setAsyncSupported(true,null);
                    }
                }
                else
                    filter.doFilter(request, response, this);

                return;
            }

            // Call servlet
            HttpServletRequest srequest = (HttpServletRequest)request;
            if (_servletHolder == null)
                notFound(Request.getBaseRequest(request), srequest, (HttpServletResponse)response);
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("call servlet {}", _servletHolder);
                _servletHolder.handle(_baseRequest,request, response);
            }    
        }
