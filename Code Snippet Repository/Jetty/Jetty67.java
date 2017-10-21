        public Void call() throws Exception
        {            
            if (_stat != null)
                _stat.start();
            if (_parser != null)
                _parser.parse(_handlers, _resource); 
            if (_stat != null)
                _stat.end();
            return null;
        }
