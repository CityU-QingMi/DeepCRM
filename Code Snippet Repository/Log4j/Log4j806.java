        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append(super.toString());
            builder.append("[action=");
            builder.append(action);
            builder.append(", manager=");
            builder.append(manager);
            builder.append(", isComplete()=");
            builder.append(isComplete());
            builder.append(", isInterrupted()=");
            builder.append(isInterrupted());
            builder.append("]");
            return builder.toString();
        }
