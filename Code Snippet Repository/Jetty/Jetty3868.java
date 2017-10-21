        public boolean setInitParameter(String name, String value)
        {
            illegalStateIfContextStarted();
            if (name == null) {
                throw new IllegalArgumentException("init parameter name required");
            }
            if (value == null) {
                throw new IllegalArgumentException("non-null value required for init parameter " + name);
            }
            if (Holder.this.getInitParameter(name)!=null)
                return false;
            Holder.this.setInitParameter(name,value);
            return true;
        }
