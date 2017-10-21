        public PropertyInfo(String name, Class type, Object value) {
            if (name == null) {
                throw new IllegalArgumentException("Name must not be null");
            }
            if (type == null) {
                throw new IllegalArgumentException("Type must not be null");
            }
            this.name = name;
            this.type = type;
            this.value = value;
        }
