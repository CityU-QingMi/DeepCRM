        @Override
        protected Action process() throws Throwable
        {
            while (true)
            {
                HttpGenerator.Result result = generator.generateRequest(null, null, chunkBuffer, null, true);
                if (LOG.isDebugEnabled())
                    LOG.debug("Generated trailers {}/{}", result, generator);
                switch (result)
                {
                    case NEED_CHUNK_TRAILER:
                    {
                        chunkBuffer = httpClient.getByteBufferPool().acquire(httpClient.getRequestBufferSize(), false);
                        break;
                    }
                    case FLUSH:
                    {
                        EndPoint endPoint = getHttpChannel().getHttpConnection().getEndPoint();
                        endPoint.write(this, chunkBuffer);
                        return Action.SCHEDULED;
                    }
                    case SHUTDOWN_OUT:
                    {
                        shutdownOutput();
                        return Action.SUCCEEDED;
                    }
                    case DONE:
                    {
                        return Action.SUCCEEDED;
                    }
                    default:
                    {
                        throw new IllegalStateException(result.toString());
                    }
                }
            }
        }
