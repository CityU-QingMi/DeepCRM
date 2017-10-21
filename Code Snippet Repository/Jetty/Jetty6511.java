    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        b.append(OpCode.name((byte)(finRsvOp & 0x0F)));
        b.append('[');
        b.append("len=").append(getPayloadLength());
        b.append(",fin=").append((finRsvOp & 0x80) != 0);
        b.append(",rsv=");
        b.append(((finRsvOp & 0x40) != 0)?'1':'.');
        b.append(((finRsvOp & 0x20) != 0)?'1':'.');
        b.append(((finRsvOp & 0x10) != 0)?'1':'.');
        b.append(",masked=").append(masked);
        b.append(']');
        return b.toString();
    }
