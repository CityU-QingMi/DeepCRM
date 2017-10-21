    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        if (_filtered)
        {
            Continuation c = (Continuation) request.getAttribute(Continuation.ATTRIBUTE);
            FilteredContinuation fc;
            if (_faux && (c==null || !(c instanceof FauxContinuation)))
            {
                fc = new FauxContinuation(request);
                request.setAttribute(Continuation.ATTRIBUTE,fc);
            }
            else
                fc=(FilteredContinuation)c;

            boolean complete=false;
            while (!complete)
            {
                try
                {
                    if (fc==null || (fc).enter(response))
                        chain.doFilter(request,response);
                }
                catch (ContinuationThrowable e)
                {
                    debug("faux",e);
                }
                finally
                {
                    if (fc==null)
                        fc = (FilteredContinuation) request.getAttribute(Continuation.ATTRIBUTE);

                    complete=fc==null || (fc).exit();
                }
            }
        }
        else
        {
            try
            {
                chain.doFilter(request,response);
            }
            catch (ContinuationThrowable e)
            {
                debug("caught",e);
            }
        }
    }
