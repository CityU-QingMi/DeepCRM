    public StructuredDataId(final String name, final String[] required, final String[] optional,
                               final int maxLength) {
        int index = -1;
        if (name != null) {
            if (maxLength > 0 && name.length() > MAX_LENGTH) {
                throw new IllegalArgumentException(String.format("Length of id %s exceeds maximum of %d characters",
                        name, maxLength));
            }
            index = name.indexOf(AT_SIGN);
        }

        if (index > 0) {
            this.name = name.substring(0, index);
            this.enterpriseNumber = Integer.parseInt(name.substring(index + 1));
        } else {
            this.name = name;
            this.enterpriseNumber = RESERVED;
        }
        this.required = required;
        this.optional = optional;
    }
