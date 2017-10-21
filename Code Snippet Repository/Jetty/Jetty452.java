        @Override
        public String toString()
        {
            return String.format("%s@%x[name=%s,fileName=%s,length=%d,headers=%s]",
                    getClass().getSimpleName(),
                    hashCode(),
                    name,
                    fileName,
                    content.getLength(),
                    fields);
        }
