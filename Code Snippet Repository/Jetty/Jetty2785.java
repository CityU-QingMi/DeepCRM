        @Override
        public void requestDestroyed(ServletRequestEvent sre)
        {
            String cname=findContextName(sre.getServletContext());
            HttpServletRequest r = (HttpServletRequest)sre.getServletRequest();
            String rname=findRequestName(r);
            DispatcherType d = r.getDispatcherType();
            if (sre.getServletRequest().isAsyncStarted())
            {
                sre.getServletRequest().getAsyncContext().addListener(_asyncListener);
                log("<< %s ctx=%s r=%s async=true",d,cname,rname);
            }
            else
            {
                Request br=Request.getBaseRequest(r);
                String headers=_showHeaders?("\n"+br.getResponse().getHttpFields().toString()):"";
                log("<< %s ctx=%s r=%s async=false %d%s",d,cname,rname,Request.getBaseRequest(r).getResponse().getStatus(),headers);
            }
        }
