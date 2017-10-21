        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append(super.toString());
            builder.append("[formatters=");
            builder.append(Arrays.toString(formatters));
            builder.append(", replace=");
            builder.append(replace);
            builder.append("]");
            return builder.toString();
        }
