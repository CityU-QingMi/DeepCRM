        @Override
        public void parsedHeader(HttpField field)
        {
            _fields.add(field);
            _hdr[++_headers] = field.getName();
            _val[_headers] = field.getValue();

            if (field instanceof HostPortHttpField)
            {
                HostPortHttpField hpfield = (HostPortHttpField)field;
                _host = hpfield.getHost();
                _port = hpfield.getPort();
            }
        }
