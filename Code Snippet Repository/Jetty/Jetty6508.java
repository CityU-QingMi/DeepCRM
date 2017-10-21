    protected void copyHeaders(Frame frame)
    {
        finRsvOp = 0x00;
        finRsvOp |= frame.isFin()?0x80:0x00;
        finRsvOp |= frame.isRsv1()?0x40:0x00;
        finRsvOp |= frame.isRsv2()?0x20:0x00;
        finRsvOp |= frame.isRsv3()?0x10:0x00;
        finRsvOp |= frame.getOpCode() & 0x0F;

        masked = frame.isMasked();
        if (masked)
        {
            mask = frame.getMask();
        }
        else
        {
            mask = null;
        }
    }
