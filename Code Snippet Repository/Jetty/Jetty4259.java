        public ConfiguredHeader(String name, String value, boolean add, boolean date)
        {
            _name = name;
            _value = value;
            _add = add;
            _date = date;

            if (_date)
            {
                _msOffset = Long.parseLong(_value);
            }
        }
