    private ReadMode readParse(ByteBuffer buffer)
    {
        EndPoint endPoint = getEndPoint();
        try
        {
            // Process the content from the Endpoint next
            while(true)  // TODO: should this honor the LogicalConnection.suspend() ?
            {
                int filled = endPoint.fill(buffer);
                if (filled < 0)
                {
                    LOG.debug("read - EOF Reached (remote: {})",getRemoteAddress());
                    ioState.onReadFailure(new EOFException("Remote Read EOF"));
                    return ReadMode.EOF;
                }
                else if (filled == 0)
                {
                    // Done reading, wait for next onFillable
                    return ReadMode.PARSE;
                }

                if (LOG.isDebugEnabled())
                {
                    LOG.debug("Filled {} bytes - {}",filled,BufferUtil.toDetailString(buffer));
                }
                parser.parse(buffer);
            }
        }
        catch (IOException e)
        {
            LOG.warn(e);
            close(StatusCode.PROTOCOL,e.getMessage());
            return ReadMode.DISCARD;
        }
        catch (CloseException e)
        {
            LOG.debug(e);
            close(e.getStatusCode(),e.getMessage());
            return ReadMode.DISCARD;
        }
        catch (Throwable t)
        {
            LOG.warn(t);
            close(StatusCode.ABNORMAL,t.getMessage());
            // TODO: should probably only switch to discard if a non-ws-endpoint error
            return ReadMode.DISCARD;
        }
    }
