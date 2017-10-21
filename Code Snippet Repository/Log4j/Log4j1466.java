        private String[] split(String value, Field field) {
            if (field.isAnnotationPresent(Option.class)) {
                String regex = field.getAnnotation(Option.class).split();
                return regex.length() == 0 ? new String[] {value} : value.split(regex);
            }
            if (field.isAnnotationPresent(Parameters.class)) {
                String regex = field.getAnnotation(Parameters.class).split();
                return regex.length() == 0 ? new String[] {value} : value.split(regex);
            }
            return new String[] {value};
        }
