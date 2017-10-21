    protected ByteBuffer onUpgradeFrom()
    {
        if (BufferUtil.hasContent(buffer))
        {
            ByteBuffer upgradeBuffer = ByteBuffer.allocate(buffer.remaining());
            upgradeBuffer.put(buffer).flip();
            return upgradeBuffer;
        }
        return null;
    }
