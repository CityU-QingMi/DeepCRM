        @Override
        public void onComplete(AsyncEvent event) throws IOException
        {
            AsyncContextEvent ace=(AsyncContextEvent)event;
            String cname=findContextName(ace.getServletContext());
            String rname=findRequestName(ace.getAsyncContext().getRequest());
            
            Request br=Request.getBaseRequest(ace.getAsyncContext().getRequest());
            Response response = br.getResponse();
            String headers=_showHeaders?("\n"+response.getHttpFields().toString()):"";
            
            log("!  ctx=%s r=%s onComplete %s %d%s",cname,rname,ace.getHttpChannelState(),response.getStatus(),headers);
        }
