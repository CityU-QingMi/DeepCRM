    private byte next(ByteBuffer buffer)
    {
        byte ch = buffer.get();

        CharState s = __charState[0xff & ch];
        switch(s)
        {
            case ILLEGAL:
                throw new IllegalCharacterException(_state,ch,buffer);

            case LF:
                _cr=false;
                break;

            case CR:
                if (_cr)
                    throw new BadMessageException("Bad EOL");

                _cr=true;
                if (buffer.hasRemaining())
                {
                    // Don't count the CRs and LFs of the chunked encoding.
                    if (_maxHeaderBytes>0 && (_state == State.HEADER || _state == State.TRAILER))
                        _headerBytes++;
                    return next(buffer);
                }

                // Can return 0 here to indicate the need for more characters,
                // because a real 0 in the buffer would cause a BadMessage below
                return 0;

            case LEGAL:
                if (_cr)
                    throw new BadMessageException("Bad EOL");
        }

        return ch;
    }
