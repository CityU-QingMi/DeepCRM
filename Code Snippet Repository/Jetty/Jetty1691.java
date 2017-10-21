    @Override
    public boolean flush(ByteBuffer... buffers) throws IOException
    {
        boolean flushed=true;
        for (ByteBuffer b : buffers)
        {
            if (b.hasRemaining())
            {
                int position = b.position();
                ByteBuffer view=b.slice();
                flushed&=super.flush(b);
                int l=b.position()-position;
                view.limit(view.position()+l);
                notifyOutgoing(view);
                if (!flushed)
                    break;
            }
        }
        return flushed;
    }
