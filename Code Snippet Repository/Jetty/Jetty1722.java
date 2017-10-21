    @Override
    public String toConnectionString()
    {
        ByteBuffer b = _encryptedInput;
        int ei=b==null?-1:b.remaining();
        b = _encryptedOutput;
        int eo=b==null?-1:b.remaining();
        b = _decryptedInput;
        int di=b==null?-1:b.remaining();

        Connection connection = _decryptedEndPoint.getConnection();
        return String.format("%s@%x{%s,eio=%d/%d,di=%d}=>%s",
                getClass().getSimpleName(),
                hashCode(),
                _sslEngine.getHandshakeStatus(),
                ei,eo,di,
                connection instanceof AbstractConnection ? ((AbstractConnection)connection).toConnectionString() : connection);
    }
