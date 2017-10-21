        public void setGenericFromType(Type type, int index)
        {
            // debug("setGenericFromType(%s,%d)",toShortName(type),index);
            this.genericType = type;
            this.genericIndex = index;
            if (type instanceof Class)
            {
                this.genericClass = (Class<?>)type;
            }
        }
