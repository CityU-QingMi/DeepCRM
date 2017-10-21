        private boolean parse(ByteBuffer buffer)
        {
            // parse fields
            while (buffer.hasRemaining())
            {
                byte b = buffer.get();
                if (_fields<6)
                {
                    if (b==' ' || b=='\r' && _fields==5)
                    {
                        _field[_fields++]=_builder.toString();
                        _builder.setLength(0);
                    }
                    else if (b<' ')
                    {
                        LOG.warn("Bad character {} for {}",b&0xFF,getEndPoint());
                        close();
                        return false;
                    }
                    else
                    {
                        _builder.append((char)b);
                    }
                }
                else
                {
                    if (b=='\n')
                    {
                        _fields=7;
                        return true;
                    }

                    LOG.warn("Bad CRLF for {}",getEndPoint());
                    close();
                    return false;
                }
            }
            
            return true;
        }
