        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();
            builder.append("GenericRef [baseClass=");
            builder.append(baseClass);
            builder.append(", ifaceClass=");
            builder.append(ifaceClass);
            builder.append(", genericType=");
            builder.append(genericType);
            builder.append(", genericClass=");
            builder.append(genericClass);
            builder.append("]");
            return builder.toString();
        }
