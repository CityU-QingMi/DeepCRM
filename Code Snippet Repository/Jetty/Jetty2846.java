        private boolean reset(MetaData.Response info, boolean head, ByteBuffer content, boolean last, Callback callback)
        {
            if (reset())
            {
                _info = info;
                _head = head;
                _content = content;
                _lastContent = last;
                _callback = callback;
                _header = null;
                _shutdownOut = false;
                return true;
            }

            if (isClosed())
                callback.failed(new EofException());
            else
                callback.failed(new WritePendingException());
            return false;
        }
