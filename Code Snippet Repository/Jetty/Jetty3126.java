    private void addTrailer()
    {
        int i=_buffer.limit();
        _buffer.limit(i+8);

        int v=(int)_crc.getValue();
        _buffer.put(i++,(byte)(v & 0xFF));
        _buffer.put(i++,(byte)((v>>>8) & 0xFF));
        _buffer.put(i++,(byte)((v>>>16) & 0xFF));
        _buffer.put(i++,(byte)((v>>>24) & 0xFF));

        v=_deflater.getTotalIn();
        _buffer.put(i++,(byte)(v & 0xFF));
        _buffer.put(i++,(byte)((v>>>8) & 0xFF));
        _buffer.put(i++,(byte)((v>>>16) & 0xFF));
        _buffer.put(i++,(byte)((v>>>24) & 0xFF));
    }
