        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LiteralAllowedMethod that = (LiteralAllowedMethod) o;

            return allowedMethod.equals(that.allowedMethod);

        }
