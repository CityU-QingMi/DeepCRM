        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PropertyInfo)) return false;

            final PropertyInfo propertyInfo = (PropertyInfo) o;

            if (!name.equals(propertyInfo.name)) return false;
            if (!type.equals(propertyInfo.type)) return false;
            if (value != null ? !value.equals(propertyInfo.value) : propertyInfo.value != null) return false;

            return true;
        }
