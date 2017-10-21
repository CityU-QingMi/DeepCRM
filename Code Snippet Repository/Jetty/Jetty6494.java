    private void assertSanePayloadLength(long len)
    {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} Payload Length: {} - {}",policy.getBehavior(),len,this);
        }

        // Since we use ByteBuffer so often, having lengths over Integer.MAX_VALUE is really impossible.
        if (len > Integer.MAX_VALUE)
        {
            // OMG! Sanity Check! DO NOT WANT! Won't anyone think of the memory!
            throw new MessageTooLargeException("[int-sane!] cannot handle payload lengths larger than " + Integer.MAX_VALUE);
        }

        switch (frame.getOpCode())
        {
            case OpCode.CLOSE:
                if (len == 1)
                {
                    throw new ProtocolException("Invalid close frame payload length, [" + payloadLength + "]");
                }
                // fall thru
            case OpCode.PING:
            case OpCode.PONG:
                if (len > ControlFrame.MAX_CONTROL_PAYLOAD)
                {
                    throw new ProtocolException("Invalid control frame payload length, [" + payloadLength + "] cannot exceed ["
                            + ControlFrame.MAX_CONTROL_PAYLOAD + "]");
                }
                break;
            case OpCode.TEXT:
                policy.assertValidTextMessageSize((int)len);
                break;
            case OpCode.BINARY:
                policy.assertValidBinaryMessageSize((int)len);
                break;
        }
    }
