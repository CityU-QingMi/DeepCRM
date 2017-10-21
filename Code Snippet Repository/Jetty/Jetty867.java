    @Override
    public Result parse(ByteBuffer buffer)
    {
        while (buffer.hasRemaining())
        {
            switch (state)
            {
                case LENGTH:
                {
                    contentLength = getContentLength();
                    state = State.CONTENT;
                    break;
                }
                case CONTENT:
                {
                    int length = Math.min(contentLength, buffer.remaining());
                    int limit = buffer.limit();
                    buffer.limit(buffer.position() + length);
                    ByteBuffer slice = buffer.slice();
                    buffer.position(buffer.limit());
                    buffer.limit(limit);
                    contentLength -= length;
                    if (onContent(slice))
                        return Result.ASYNC;
                    if (contentLength > 0)
                        break;
                    state = State.LENGTH;
                    return Result.COMPLETE;
                }
                default:
                {
                    throw new IllegalStateException();
                }
            }
        }
        return Result.PENDING;
    }
