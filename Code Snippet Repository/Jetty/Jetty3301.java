        @Override
        protected HttpChannelOverHttp newHttpChannel()
        {
            return new HttpChannelOverHttp(this, getConnector(), getHttpConfiguration(), getEndPoint(), this)
            {
                @Override
                public boolean startRequest(String method, String uri, HttpVersion version)
                {
                    getRequest().setAttribute("DispatchedAt",((ExtendedEndPoint)getEndPoint()).getLastSelected());
                    return super.startRequest(method,uri,version);
                }
            };
        }
