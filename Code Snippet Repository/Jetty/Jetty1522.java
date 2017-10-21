        @Override
        public boolean startResponse(HttpVersion version, int status, String reason)
        {
            _fields.clear();
            _trailers.clear();
            _methodOrVersion = version.asString();
            _uriOrStatus = Integer.toString(status);
            _versionOrReason = reason;
            _hdr = new String[9];
            _val = new String[9];
            _messageCompleted = false;
            _headerCompleted = false;
            return false;
        }
