    private static char[] getQBuf() {
        char[] _qbuf = _qbufLocal.get();
        if (_qbuf == null) {
            _qbuf = new char[6];
            _qbuf[0] = '\\';
            _qbuf[2] = '0';
            _qbuf[3] = '0';

            _qbufLocal.set(_qbuf);
        }
        return _qbuf;
    }
