        @Override
        public String toString()
        {
            return new StringBuffer()
                .append("\n=== ").append(getClass().getSimpleName()).append(" ===")
                .append("\ntitle: ").append(getTitle())
                .append("\nboolean1: ").append(isBoolean1())
                .append("\nnullTest: ").append(getNullTest())
                .append("\nbaz: ").append(getBaz())
                .append("\ncolor: ").append(_color).toString();
        }
