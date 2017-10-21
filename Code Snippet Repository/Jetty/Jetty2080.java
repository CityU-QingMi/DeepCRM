    protected void performTest(MonitorAction action, long count, long interval)
        throws Exception
    {
        _monitor.addActions(action);

        for (long cnt=0; cnt < count; cnt++)
        {
            try
            {
                //LOG.debug("Request: %s", _requestUrl);
                ContentResponse r3sponse = _client.GET(_requestUrl);           
                
                //ContentExchange getExchange = new ContentExchange();
                //getExchange.setURL(_requestUrl);
                //getExchange.setMethod(HttpMethods.GET);

                //_client.send(getExchange);
                //int state = getExchange.waitForDone();

                String content = "";
                //int responseStatus = getExchange.getResponseStatus();
                if (r3sponse.getStatus() == HttpStatus.OK_200)
                {
                    content = r3sponse.getContentAsString();
                }
                else 
                {
                    LOG.info("response status", r3sponse.getStatus());
                }

                assertEquals(HttpStatus.OK_200,r3sponse.getStatus());
                Thread.sleep(interval);
            }
            catch (InterruptedException ex)
            {
                break;
            }
        }

        Thread.sleep(interval);

        _monitor.removeActions(action);
    }
