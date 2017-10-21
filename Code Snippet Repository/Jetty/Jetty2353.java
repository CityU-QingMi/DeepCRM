        @Override
        public void onWritePossible() throws IOException
        {
            ServletOutputStream output = clientRequest.getAsyncContext().getResponse().getOutputStream();

            // If we had a pending write, let's succeed it.
            if (writePending)
            {
                if (_log.isDebugEnabled())
                    _log.debug("{} pending async write complete of {} on {}", getRequestId(clientRequest), chunk, output);
                writePending = false;
                if (succeed(chunk.callback))
                    return;
            }

            int length = 0;
            DeferredContentProvider.Chunk chunk = null;
            while (output.isReady())
            {
                if (chunk != null)
                {
                    if (_log.isDebugEnabled())
                        _log.debug("{} async write complete of {} ({} bytes) on {}", getRequestId(clientRequest), chunk, length, output);
                    if (succeed(chunk.callback))
                        return;
                }

                this.chunk = chunk = chunks.poll();
                if (chunk == null)
                    return;

                length = chunk.buffer.remaining();
                if (length > 0)
                    writeProxyResponseContent(output, chunk.buffer);
            }

            if (_log.isDebugEnabled())
                _log.debug("{} async write pending of {} ({} bytes) on {}", getRequestId(clientRequest), chunk, length, output);
            writePending = true;
        }
