        @Override
        public boolean setInitParameter(String name, String value)
        {
            if (!isStarting())
                throw new IllegalStateException();

            if (!_enabled)
                throw new UnsupportedOperationException();

            return super.setInitParameter(name,value);
        }
