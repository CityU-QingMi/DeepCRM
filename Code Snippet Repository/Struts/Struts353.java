        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MessageFormatKey that = (MessageFormatKey) o;

            if (pattern != null ? !pattern.equals(that.pattern) : that.pattern != null) return false;
            return locale != null ? locale.equals(that.locale) : that.locale == null;
        }
