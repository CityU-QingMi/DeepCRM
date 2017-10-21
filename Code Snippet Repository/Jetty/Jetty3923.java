        @Override
        public String toString()
        {
            StringBuilder b = new StringBuilder();
            for(FilterHolder f: _chain)
            {
                b.append(f.toString());
                b.append("->");
            }
            b.append(_servletHolder);
            return b.toString();
        }
