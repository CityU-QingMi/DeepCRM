    @Override
    public String matchAndApply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if (request.isSecure())
        {
            String user_agent = request.getHeader(HttpHeader.USER_AGENT.asString());
            
            if (user_agent!=null)
            {
                int msie=user_agent.indexOf("MSIE");
                if (msie>0 && user_agent.length()-msie>5)
                {
                    // Get Internet Explorer Version
                    int ieVersion = user_agent.charAt(msie+5);
                    
                    if ( ieVersion<=IEv5)
                    {
                        response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
                        return target;
                    }

                    if (ieVersion==IEv6)
                    {
                        int windows = user_agent.indexOf("Windows",msie+5);
                        if (windows>0)
                        {
                            int end=user_agent.indexOf(')',windows+8);
                            if(end<0 || __IE6_BadOS.get(user_agent,windows+8,end-windows-8)!=null)
                            {
                                response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
                                return target;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
