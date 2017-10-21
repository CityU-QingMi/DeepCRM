        @Override
        public void onSuccess(final Response serverResponse)
        {
            try
            {
                if (hasContent)
                {
                    // If we had unknown length content, we need to call the
                    // transformer to signal that the content is finished.
                    if (contentLength < 0)
                    {
                        ProxyWriter proxyWriter = (ProxyWriter)clientRequest.getAttribute(WRITE_LISTENER_ATTRIBUTE);
                        ContentTransformer transformer = (ContentTransformer)clientRequest.getAttribute(SERVER_TRANSFORMER_ATTRIBUTE);

                        transform(transformer, BufferUtil.EMPTY_BUFFER, true, buffers);

                        long newContentBytes = 0;
                        int size = buffers.size();
                        if (size > 0)
                        {
                            Callback callback = size == 1 ? complete : new CountingCallback(complete, size);
                            for (int i = 0; i < size; ++i)
                            {
                                ByteBuffer buffer = buffers.get(i);
                                newContentBytes += buffer.remaining();
                                proxyWriter.offer(buffer, callback);
                            }
                            buffers.clear();
                        }
                        else
                        {
                            proxyWriter.offer(BufferUtil.EMPTY_BUFFER, complete);
                        }

                        if (_log.isDebugEnabled())
                            _log.debug("{} downstream content transformation to {} bytes", getRequestId(clientRequest), newContentBytes);

                        proxyWriter.onWritePossible();
                    }
                }
                else
                {
                    complete.succeeded();
                }
            }
            catch (Throwable x)
            {
                complete.failed(x);
            }
        }
