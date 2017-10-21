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
            BufferUtil.writeTo(frame.getPayload(),out);
            out.flush();
            if (callback != null)
            {
                callback.writeSuccess();
            }
        }
        catch (IOException e)
        {
            if (callback != null)
            {
                callback.writeFailed(e);
            }
        }
        finally
        {
            bufferPool.release(headerBuf);
        }

        if (frame.getOpCode() == OpCode.CLOSE)
        {
            disconnect();
        }
    }
