    protected void appendByte(byte b) throws IOException
    {

        if (b > 0 && _state == UTF8_ACCEPT)
        {
            _appendable.append((char)(b & 0xFF));
        }
        else
        {
            int i = b & 0xFF;
            int type = BYTE_TABLE[i];
            _codep = _state == UTF8_ACCEPT ? (0xFF >> type) & i : (i & 0x3F) | (_codep << 6);
            int next = TRANS_TABLE[_state + type];

            switch(next)
            {
                case UTF8_ACCEPT:
                    _state=next;
                    if (_codep < Character.MIN_HIGH_SURROGATE)
                    {
                        _appendable.append((char)_codep);
                    }
                    else
                    {
                        for (char c : Character.toChars(_codep))
                            _appendable.append(c);
                    }
                    break;
                    
                case UTF8_REJECT:
                    String reason = "byte "+TypeUtil.toHexString(b)+" in state "+(_state/12);
                    _codep=0;
                    _state = UTF8_ACCEPT;
                    _appendable.append(REPLACEMENT);
                    throw new NotUtf8Exception(reason);
                    
                default:
                    _state=next;
                    
            }
        }
    }
