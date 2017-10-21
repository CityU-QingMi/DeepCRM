        @Override
        public int read() throws IOException
        {
            if (_buffer==null || _pos>= _buffer.length)
            {
                //Any CR and LF will be consumed by the readLine() call.
                //We need to put them back into the bytes returned from this
                //method because the parsing of the multipart content uses them
                //as markers to determine when we've reached the end of a part.
                _line = _in.readLine();
                if (_line==null)
                    return -1;  //nothing left
                if (_line.startsWith("--"))
                    _buffer=(_line+"\r\n").getBytes(); //boundary marking end of part
                else if (_line.length()==0)
                    _buffer="\r\n".getBytes(); //blank line
                else
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream((4*_line.length()/3)+2);
                    B64Code.decode(_line, baos);
                    baos.write(13);
                    baos.write(10);
                    _buffer = baos.toByteArray();
                }

                _pos=0;
            }

            return _buffer[_pos++];
        }
