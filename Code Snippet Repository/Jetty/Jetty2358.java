        @Override
        protected Action process() throws Exception
        {
            int requestId = _log.isDebugEnabled() ? getRequestId(request) : 0;
            ServletInputStream input = request.getInputStream();

            while (input.isReady())
            {
                int read = input.read(buffer);
                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous read {} bytes on {}", requestId, read, input);
                if (read > 0)
                {
                    if (_log.isDebugEnabled())
                        _log.debug("{} proxying content to upstream: {} bytes", requestId, read);
                    onRequestContent(request, proxyRequest, provider, buffer, 0, read, this);
                    return Action.SCHEDULED;
                }
                else if (read < 0)
                {
                    if (_log.isDebugEnabled())
                        _log.debug("{} asynchronous read complete on {}", requestId, input);
                    return Action.SUCCEEDED;
                }
            }

            if (_log.isDebugEnabled())
                _log.debug("{} asynchronous read pending on {}", requestId, input);
            return Action.IDLE;
        }
