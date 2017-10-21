        @Override
        public void onWritePossible() throws IOException
        {
            int requestId = getRequestId(request);
            ServletOutputStream output = request.getAsyncContext().getResponse().getOutputStream();
            if (state == WriteState.READY)
            {
                // There is data to write.
                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous write start of {} bytes on {}", requestId, length, output);
                output.write(buffer, offset, length);
                state = WriteState.PENDING;
                if (output.isReady())
                {
                    if (_log.isDebugEnabled())
                        _log.debug("{} asynchronous write of {} bytes completed on {}", requestId, length, output);
                    complete();
                }
                else
                {
                    if (_log.isDebugEnabled())
                        _log.debug("{} asynchronous write of {} bytes pending on {}", requestId, length, output);
                }
            }
            else if (state == WriteState.PENDING)
            {
                // The write blocked but is now complete.
                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous write of {} bytes completing on {}", requestId, length, output);
                complete();
            }
            else
            {
                throw new IllegalStateException();
            }
        }
