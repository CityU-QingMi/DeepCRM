        @Override
        public String toString()
        {
            StringBuilder s = new StringBuilder();
            s.append(path).append(" [depth=");
            if (recurseDepth==UNLIMITED_DEPTH)
                s.append("UNLIMITED");
            else
                s.append(recurseDepth);
            s.append(']');
            return s.toString();
        }
