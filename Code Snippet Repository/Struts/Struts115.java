        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PatternAllowedMethod that = (PatternAllowedMethod) o;

            return allowedMethodPattern.pattern().equals(that.allowedMethodPattern.pattern());

        }
