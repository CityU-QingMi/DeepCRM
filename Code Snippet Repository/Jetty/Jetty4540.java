        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();
            builder.append("Prop [key=");
            builder.append(key);
            builder.append(", value=");
            builder.append(value);
            builder.append(", origin=");
            builder.append(origin);
            builder.append(", overrides=");
            builder.append(overrides);
            builder.append("]");
            return builder.toString();
        }
