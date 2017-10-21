    @Override
    public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        ByteBuffer headerBuf = generator.generateHeaderBytes(frame);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("writing out: {}",BufferUtil.toDetailString(headerBuf));
        }

        try
        {
            BufferUtil.writeTo(headerBuf,out);
            if (frame.hasPayload())
                BufferUtil.writeTo(frame.getPayload(),out);
            out.flush();
            if (callback != null)
            {
                callback.writeSuccess();
            }

            if (frame.getOpCode() == OpCode.CLOSE)
            {
                disconnect();
            }
        }
        catch (Throwable t)
        {
            if (callback != null)
            {
                callback.writeFailed(t);
            }
        }
    }
