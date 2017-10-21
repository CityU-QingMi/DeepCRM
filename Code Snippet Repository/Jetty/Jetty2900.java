        @Override
        public void onFillable()
        {
            try
            {
                while(BufferUtil.space(_buffer)>0)
                {
                    // Read data
                    int fill=getEndPoint().fill(_buffer);
                    if (fill<0)
                    {
                        getEndPoint().shutdownOutput();
                        return;
                    }
                    if (fill==0)
                    {
                        fillInterested();
                        return;
                    }
                }

                // Is it a V1?
                switch(_buffer.get(0))
                {
                    case 'P':
                    {
                        ProxyProtocolV1Connection v1 = new ProxyProtocolV1Connection(getEndPoint(),_connector,_next,_buffer);
                        getEndPoint().upgrade(v1);
                        return;
                    }
                    case 0x0D:
                    {
                        ProxyProtocolV2Connection v2 = new ProxyProtocolV2Connection(getEndPoint(),_connector,_next,_buffer);
                        getEndPoint().upgrade(v2);
                        return;
                    }
                    default:       
                        LOG.warn("Not PROXY protocol for {}",getEndPoint());
                        close();  
                }
            }
            catch (Throwable x)
            {
                LOG.warn("PROXY error for "+getEndPoint(),x);
                close();
            }
        }
