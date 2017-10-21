        @Override
        public void requestInitialized(ServletRequestEvent sre)
        {
            String cname=findContextName(sre.getServletContext());
            HttpServletRequest r = (HttpServletRequest)sre.getServletRequest();
           
            String rname=findRequestName(r);
            DispatcherType d = r.getDispatcherType();
            if (d==DispatcherType.REQUEST)
            {
                Request br=Request.getBaseRequest(r);

                String headers=_showHeaders?("\n"+br.getMetaData().getFields().toString()):"";
                
                
                StringBuffer url=r.getRequestURL();
                if (r.getQueryString()!=null)
                    url.append('?').append(r.getQueryString());
                log(">> %s ctx=%s r=%s %s %s %s %s %s%s",d,
                        cname,
                        rname,
                        d,
                        r.getMethod(),
                        url.toString(),
                        r.getProtocol(),
                        br.getHttpChannel(),
                        headers);
            }
            else
                log(">> %s ctx=%s r=%s",d,cname,rname);
        }
