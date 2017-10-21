    public void assertValid()
    {
        if (isControlFrame())
        {
            if (getPayloadLength() > ControlFrame.MAX_CONTROL_PAYLOAD)
            {
                throw new ProtocolException("Desired payload length [" + getPayloadLength() + "] exceeds maximum control payload length ["
                        + MAX_CONTROL_PAYLOAD + "]");
            }

            if ((finRsvOp & 0x80) == 0)
            {
                throw new ProtocolException("Cannot have FIN==false on Control frames");
            }

            if ((finRsvOp & 0x40) != 0)
            {
                throw new ProtocolException("Cannot have RSV1==true on Control frames");
            }

            if ((finRsvOp & 0x20) != 0)
            {
                throw new ProtocolException("Cannot have RSV2==true on Control frames");
            }

            if ((finRsvOp & 0x10) != 0)
            {
                throw new ProtocolException("Cannot have RSV3==true on Control frames");
            }
        }
    }
