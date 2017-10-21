    private Object findDefaultValue(final LogEvent event) {
        if (this.conversionType == int.class || this.conversionType == Integer.class) {
            return this.annotation.defaultInt();
        }
        if (this.conversionType == long.class || this.conversionType == Long.class) {
            return this.annotation.defaultLong();
        }
        if (this.conversionType == boolean.class || this.conversionType == Boolean.class) {
            return this.annotation.defaultBoolean();
        }
        if (this.conversionType == float.class || this.conversionType == Float.class) {
            return this.annotation.defaultFloat();
        }
        if (this.conversionType == double.class || this.conversionType == Double.class) {
            return this.annotation.defaultDouble();
        }
        if (this.conversionType == byte.class || this.conversionType == Byte.class) {
            return this.annotation.defaultByte();
        }
        if (this.conversionType == char.class || this.conversionType == Character.class) {
            return this.annotation.defaultChar();
        }
        if (this.conversionType == short.class || this.conversionType == Short.class) {
            return this.annotation.defaultShort();
        }
        if (this.conversionType == Class.class) {
            return this.annotation.defaultClass();
        }
        return this.substitutor.replace(event, this.annotation.defaultString());
    }
