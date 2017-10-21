        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            JMXRMIServerSocketFactory that = (JMXRMIServerSocketFactory)obj;
            return Objects.equals(_host, that._host);
        }
