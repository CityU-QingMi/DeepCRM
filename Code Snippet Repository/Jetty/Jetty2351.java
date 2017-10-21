        @Override
        protected Action process() throws Exception
        {
            ServletInputStream input = clientRequest.getInputStream();
            while (input.isReady() && !input.isFinished())
            {
                int read = readClientRequestContent(input, buffer);

                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous read {} bytes on {}", getRequestId(clientRequest), read, input);

                if (read < 0)
                    return Action.SUCCEEDED;

                if (contentLength > 0 && read > 0)
                    length += read;

                ByteBuffer content = read > 0 ? ByteBuffer.wrap(buffer, 0, read) : BufferUtil.EMPTY_BUFFER;
                boolean finished = length == contentLength;
                process(content, this, finished);

                if (read > 0)
                    return Action.SCHEDULED;
            }

            if (input.isFinished())
            {
                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous read complete on {}", getRequestId(clientRequest), input);
                return Action.SUCCEEDED;
            }
            else
            {
                if (_log.isDebugEnabled())
                    _log.debug("{} asynchronous read pending on {}", getRequestId(clientRequest), input);
                return Action.IDLE;
            }
        }
