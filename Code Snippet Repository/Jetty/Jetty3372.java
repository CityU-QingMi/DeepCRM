    private void deliver(Content... content)
    {
        if (content!=null)
        {
            for (Content c: content)
            {
                if (c==EOF_CONTENT)
                {
                    _in.eof();
                    _eof = true;
                }
                else if (c==HttpInput.EARLY_EOF_CONTENT)
                {
                    _in.earlyEOF();
                    _eof = true;
                }
                else
                {
                    _in.addContent(c);
                    BufferUtil.append(_expected,c.getByteBuffer().slice());
                }
            }
        }
    }
